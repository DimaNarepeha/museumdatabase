package com.softserve.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitManager {
    public void printExhibit() {
        try (PreparedStatement selectFromExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("SELECT exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit\n" +
                        "INNER JOIN hall ON hall.id_hall=exhibit.id_hall\n" +
                        "INNER JOIN material ON material.id_material=exhibit.id_material\n" +
                        "INNER JOIN technique ON technique.id_technique=exhibit.id_technique\n" +
                        "INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit\n" +
                        "INNER JOIN author ON author.id_author=author_exhibit.id_author;")) {
            ResultSet resultSet = selectFromExhibit.executeQuery();
            System.out.println("Exhibits: ");

            while (resultSet.next()) {
                System.out.println("Exhibit : " + resultSet.getString(1));
                System.out.println("Hall : " + resultSet.getString(2));
                System.out.println("Author : " + resultSet.getString(3)
                        + " " + resultSet.getString(4));
                System.out.println("Material : " + resultSet.getString(5));
                System.out.println("Technique : " + resultSet.getString(6));
                System.out.println("===============");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addExhibit() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        printAuthors();
        printHalls();
        printMaterial();
        printTechnique();

        try (PreparedStatement insertToExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO exhibit(id_material,id_technique,id_hall,exhibit_name)" +
                        "VALUES(?,?,?,?)");
             PreparedStatement insertToAuthor_Exhibit = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO author_exhibit(id_exhibit,id_author)" +
                             "VALUES(?,?)");
             PreparedStatement selectFromExhibitLast = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_exhibit FROM exhibit ORDER BY id_exhibit DESC LIMIT 1 ")) {
            System.out.println("Please select id for material");
            int materialId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please select id for technique");
            int techniqueId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please select id for hall");
            int hallId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please select id for author");
            int authorId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please input new exhibit name");
            String exhibitName = bufferedReader.readLine();

            insertToExhibit.setInt(1, materialId);
            insertToExhibit.setInt(2, techniqueId);
            insertToExhibit.setInt(3, hallId);
            insertToExhibit.setString(4, exhibitName);
            insertToExhibit.execute();
            ResultSet resultSet = selectFromExhibitLast.executeQuery();
            resultSet.first();
            int idOfNewExhibit = resultSet.getInt(1);
            resultSet.close();
            insertToAuthor_Exhibit.setInt(1, idOfNewExhibit);
            insertToAuthor_Exhibit.setInt(2, authorId);
            insertToAuthor_Exhibit.execute();
            System.out.println("Successfully added new exhibit " + exhibitName);
        } catch (IOException e) {
            System.out.println("Invalid input");
        } catch (NumberFormatException e) {
            System.out.println("Maybe you have entered letters or space somewhere");
        } catch (SQLException e) {
            System.out.println("Database fail");
        }
    }

    public void printHalls() {
        try (PreparedStatement selectFromHalls = Database.getInstance().getConnection().prepareStatement("SELECT * FROM hall")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Hall Id : " + resultSet.getInt(1));
                System.out.println("Hall name : " + resultSet.getString(2));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAuthors() {
        try (PreparedStatement selectFromHalls = Database.getInstance().getConnection().prepareStatement("SELECT * FROM author")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Author Id : " + resultSet.getInt(1));
                System.out.println("Author first name : " + resultSet.getString(2));
                System.out.println("Author last name : " + resultSet.getString(3));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTechnique() {
        try (PreparedStatement selectFromHalls = Database.getInstance().getConnection().prepareStatement("SELECT * FROM technique")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Technique Id : " + resultSet.getInt(1));
                System.out.println("Technique name : " + resultSet.getString(2));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMaterial() {
        try (PreparedStatement selectFromHalls = Database.getInstance().getConnection().prepareStatement("SELECT * FROM material")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Material Id : " + resultSet.getInt(1));
                System.out.println("Material name : " + resultSet.getString(2));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(BufferedReader reader) {
        printAuthors();
        System.out.println("select Author ID to update");
        try (PreparedStatement updateAuthor = Database.getInstance().getConnection().prepareStatement("UPDATE author SET FIRSTNAME = ?, LASTNAME = ? WHERE id_author= ?")) {
            int authorId = Integer.parseInt(reader.readLine());
            System.out.println("Print new First name :");
            String newName = reader.readLine();
            System.out.println("Print new Last name :");
            String newLastName = reader.readLine();
            updateAuthor.setString(1, newName);
            updateAuthor.setString(2, newLastName);
            updateAuthor.setInt(3, authorId);
            int rowsAffected = updateAuthor.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated " + rowsAffected + " row");
            } else {
                System.out.println("Nothing was updated");
            }
        } catch (IOException e) {
            System.out.println("Invalid input");
        } catch (NumberFormatException e) {
            System.out.println("Maybe you have entered letters or space somewhere");
        } catch (SQLException e) {
            System.out.println("Database fail");
        }

    }
}
