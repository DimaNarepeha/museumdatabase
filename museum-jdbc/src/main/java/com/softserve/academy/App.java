package com.softserve.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{

    static Connection conn;
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );

        String url = "jdbc:mysql://localhost:3306/university?useSSL=false&serverTimezone=UTC";
        String username = "root"; // root
        String password = "123456"; // root, 1-8

        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected? " + !conn.isClosed());

        
    }
}
