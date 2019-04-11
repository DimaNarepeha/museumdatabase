package com.softserve.academy;

import java.io.BufferedReader;
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
        new ExhibitManager().updateAuthor(bufferedReader);


    }

    public static void printMenu() {
        System.out.println("1.CREATE(write data)\n"
                + "2.READ\n"
                + "3.UPDATE\n"
                + "4.DELETE");
    }
}
