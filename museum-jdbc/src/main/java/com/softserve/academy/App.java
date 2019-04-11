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
        GuideManager.deleteGuide();
      //  Database database=Database.getInstance();
       //GuideManager.updateGuide();
        GuideManager.printGuide();
      /*  GuideManager.findGuidesByPeriod();
        GuideManager.findExcursionsByPeriod();
            /* GuideManager.printGuide();
             GuideManager.printPositions();
             GuideManager.printSchedule();*/
          /*   GuideManager.printExcursions();
             GuideManager.addGuide();*/
    }
}
