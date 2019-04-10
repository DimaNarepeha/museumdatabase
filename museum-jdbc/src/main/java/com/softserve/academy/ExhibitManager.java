package com.softserve.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExhibitManager {
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
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
