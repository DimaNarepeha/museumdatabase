package com.softserve.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class App {

    static Connection conn;

    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (menu(bufferedReader)) {
            continue;
        }

    }

    public static boolean menu(BufferedReader bufferedReader) {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE\n"
                + "5.EXIT");
        try {
            int input = Integer.parseInt(bufferedReader.readLine());
            switch (input) {
                case 1:
                    createMenu(bufferedReader);
                    break;
                case 2:
                    readMenu(bufferedReader);
                    break;
                case 3:
                    updateMenu(bufferedReader);
                    break;
                case 4:
                    deleteMenu(bufferedReader);
                    break;
                case 5:
                    return false;
                default:
                    System.out.println("Invalid number. Please Enter again");
                    menu(bufferedReader);
            }
        } catch (IOException e) {
            System.out.println("Invalid input. You are now in the main menu");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("You have provided invalid input(letters,spaces). You are now in the main menu");
            return true;
        }
        return true;
    }

    public static void createMenu(BufferedReader bufferedReader) throws IOException, NumberFormatException {
        System.out.println("1.Create new exhibit\n"
                + "2.Create new author");
        int input = Integer.parseInt(bufferedReader.readLine());
        switch (input) {
            case 1:
                ExhibitManager.createExhibit(bufferedReader);
                break;
            case 2:
                ExhibitManager.createAuthor(bufferedReader);
                break;
            default:
                System.out.println("Invalid number.");
                menu(bufferedReader);
        }
    }

    public static void readMenu(BufferedReader bufferedReader) throws IOException, NumberFormatException {
        System.out.println("1.Print exhibits\n"
                + "2.Print exhibits by author\n"
                + "3.Print authors\n"
                + "4.Print halls\n"
                + "5.Print techniques\n"
                + "6.Print materials");
        int input = Integer.parseInt(bufferedReader.readLine());
        switch (input) {
            case 1:
                ExhibitManager.printExhibit();
                break;
            case 2:
                ExhibitManager.printExhibitByAuthor(bufferedReader);
                break;
            case 3:
                ExhibitManager.printAuthors();
                break;
            case 4:
                ExhibitManager.printHalls();
                break;
            case 5:
                ExhibitManager.printTechnique();
                break;
            case 6:
                ExhibitManager.printMaterial();
                break;
            default:
                System.out.println("Invalid number.");
                menu(bufferedReader);
        }
    }

    public static void updateMenu(BufferedReader bufferedReader) throws IOException, NumberFormatException {
        System.out.println("1.Update authors\n");
        int input = Integer.parseInt(bufferedReader.readLine());
        switch (input) {
            case 1:
                ExhibitManager.updateAuthor(bufferedReader);
                break;
            default:
                System.out.println("Invalid number.");
                menu(bufferedReader);
        }
    }

    public static void deleteMenu(BufferedReader bufferedReader) throws IOException, NumberFormatException {
        System.out.println("1.Delete Author\n"
                + "2.Delete Exhibit");
        int input = Integer.parseInt(bufferedReader.readLine());
        switch (input) {
            case 1:
                ExhibitManager.deleteAuthor(bufferedReader);
                break;
            case 2:
                ExhibitManager.deleteExhibit(bufferedReader);
                break;
            default:
                System.out.println("Invalid number.");
                menu(bufferedReader);
        }
    }
}
