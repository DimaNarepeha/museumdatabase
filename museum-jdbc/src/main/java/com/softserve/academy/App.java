package com.softserve.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class App {

    static Connection conn;

    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



    }

    public static void menu(BufferedReader bufferedReader) {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE");
        try {
            int input = Integer.parseInt(bufferedReader.readLine());
            switch(input){
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
                    default:
                        System.out.println("Invalid number. Please Enter again");
                        menu(bufferedReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createMenu(BufferedReader bufferedReader) {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE");
        try {
            int input = Integer.parseInt(bufferedReader.readLine());
            switch(input){
                case 1:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readMenu(BufferedReader bufferedReader) {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE");
        try {
            int input = Integer.parseInt(bufferedReader.readLine());
            switch(input){
                case 1:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateMenu(BufferedReader bufferedReader) {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE");
        try {
            int input = Integer.parseInt(bufferedReader.readLine());
            switch(input){
                case 1:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteMenu(BufferedReader bufferedReader) {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE");
        try {
            int input = Integer.parseInt(bufferedReader.readLine());
            switch(input){
                case 1:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
