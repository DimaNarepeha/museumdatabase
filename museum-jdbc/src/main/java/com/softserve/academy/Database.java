package com.softserve.academy;

import com.mysql.cj.exceptions.CJCommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    Connection connectionToDatabase;


    static Database instance = null;

    private Database() {
        String url = "jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2287510?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //NOT original String url = "jdbc:mysql://localhost:3306/dictionary?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            connectionToDatabase = DriverManager.getConnection(url, "sql2287510", "wE7%rD9!");
            //NOT original connectionToDatabase = DriverManager.getConnection(url, "root", "q1w2e3r4t5y6");
        } catch (SQLException | CJCommunicationsException e) {
            e.printStackTrace();
        }

    }

    public static Database getInstance() {
        if (instance == null)
            synchronized (Database.class) {
                if (instance == null)
                    instance = new Database();

            }
        return instance;
    }

    public Connection getConnection() {
        return connectionToDatabase;
    }


}
