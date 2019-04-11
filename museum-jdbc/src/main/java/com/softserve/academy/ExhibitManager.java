package com.softserve.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitManager {
    public static void printExhibit() {
        try (PreparedStatement selectFromExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("SELECT exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name,exhibit.id_exhibit FROM exhibit\n" +
                        "INNER JOIN hall ON hall.id_hall=exhibit.id_hall\n" +
                        "INNER JOIN material ON material.id_material=exhibit.id_material\n" +
                        "INNER JOIN technique ON technique.id_technique=exhibit.id_technique\n" +
                        "INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit\n" +
                        "INNER JOIN author ON author.id_author=author_exhibit.id_author;")) {
            ResultSet resultSet = selectFromExhibit.executeQuery();
            System.out.println("Exhibits: ");

            while (resultSet.next()) {
                System.out.println("Exhibit Id : " + resultSet.getInt(7));
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

    public static void createExhibit(BufferedReader bufferedReader) {
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

    public static void printExhibitByAuthor(BufferedReader reader) {
        printAuthors();
        System.out.println("Please select author by id");
        try (PreparedStatement selectFromExhibit = Database.getInstance().getConnection().prepareStatement("SELECT  exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit" +
                "INNER JOIN hall ON hall.id_hall=exhibit.id_hall" +
                "INNER JOIN material ON material.id_material=exhibit.id_material" +
                "INNER JOIN technique ON technique.id_technique=exhibit.id_technique" +
                "INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit" +
                "INNER JOIN author ON author.id_author=author_exhibit.id_author" +
                "WHERE author.id_author=?;")) {
            int authorId = Integer.parseInt(reader.readLine());
            selectFromExhibit.setInt(1, authorId);
            ResultSet resultSet = selectFromExhibit.executeQuery();
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("No exhibits with this author");
        }
    }

    public static void printHalls() {
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

    public static void printAuthors() {
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

    public static void printTechnique() {
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

    public static void printMaterial() {
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

    public static void createAuthor(BufferedReader reader) {
        try (PreparedStatement addAuthor = Database.getInstance().getConnection().prepareStatement("INSERT INTO author(FIRSTNAME,LASTNAME) VALUES(?,?)")) {

            System.out.println("Print First name :");
            String newName = reader.readLine();
            System.out.println("Print Last name :");
            String newLastName = reader.readLine();
            addAuthor.setString(1, newName);
            addAuthor.setString(2, newLastName);
            int rowsAffected = addAuthor.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully added " + newName + " " + newLastName);
            } else {
                System.out.println("Nothing was added");
            }
        } catch (IOException e) {
            System.out.println("Invalid input");
        } catch (NumberFormatException e) {
            System.out.println("Maybe you have entered letters or space somewhere");
        } catch (SQLException e) {
            System.out.println("Database fail. Author is not added");
        }
    }

    public static void updateAuthor(BufferedReader reader) {
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

    public static void deleteAuthor(BufferedReader reader) {
        printAuthors();
        System.out.println("select Author ID to delete");
        try (PreparedStatement deleteAuthor = Database.getInstance().getConnection().prepareStatement("DELETE FROM author WHERE id_author =?")) {
            int authorId = Integer.parseInt(reader.readLine());
            deleteAuthor.setInt(1, authorId);
            int rowsAffected = deleteAuthor.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted " + rowsAffected + " row");
            } else {
                System.out.println("Nothing was deleted");
            }
        } catch (IOException e) {
            System.out.println("Invalid input");
        } catch (NumberFormatException e) {
            System.out.println("Maybe you have entered letters or space somewhere");
        } catch (SQLException e) {
            System.out.println("Database fail");
        }
    }

    public static void deleteExhibit(BufferedReader reader) {
        printExhibit();
        System.out.println("select exhibit ID to delete");
        try (PreparedStatement deleteExhibit = Database.getInstance().getConnection().prepareStatement("DELETE FROM exhibit WHERE id_exhibit = ?");
             PreparedStatement delete_author_exhibit = Database.getInstance().getConnection().prepareStatement("DELETE FROM author_exhibit WHERE id_exhibit = ?")) {
            int exhibitId = Integer.parseInt(reader.readLine());
            deleteExhibit.setInt(1, exhibitId);
            delete_author_exhibit.setInt(1, exhibitId);
            delete_author_exhibit.execute();
            int rowsAffected = deleteExhibit.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted " + rowsAffected + " exhibit");
            } else {
                System.out.println("Nothing was deleted");
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
