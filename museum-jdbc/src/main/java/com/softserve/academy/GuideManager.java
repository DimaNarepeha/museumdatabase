package com.softserve.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideManager {
    public static void printGuide() {
        Connection conn = Database.getInstance().getConnection();
        String query = "SELECT id_guide, firstname,lastname,position_name  FROM guide g join guide_position p on " +
                "g.id_position=p.id_guide_position group by id_guide";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id_guide") + "\t" +
                                "First Name: " + rs.getString("firstname") + "\t" +
                                "Last Name: " + rs.getString("lastname") + "\t" +
                                "Position: " + rs.getString("position_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printPositions() {
        try (PreparedStatement selectFromHalls = Database.getInstance().getConnection().prepareStatement("SELECT * FROM guide_position")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Position Id : " + resultSet.getInt(1));
                System.out.println("Position name : " + resultSet.getString(2));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printSchedule() {
        try (PreparedStatement selectFromHalls =
                     Database.getInstance().getConnection().prepareStatement("SELECT " +
                             "e.excursion_name,g.lastname,s.time_start FROM schedules s" +
                             " join excursions e on s.id_excursion=e.id_excursion" +
                             " join guide g on s.id_guide = g.id_guide")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Excursion name : " + resultSet.getString(1));
                System.out.println("Last name : " + resultSet.getString(2));
                System.out.println("Time start : " + resultSet.getString(3));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printExcursions() {
        try (PreparedStatement selectFromHalls = Database.getInstance().getConnection().prepareStatement("SELECT * FROM excursions")) {
            ResultSet resultSet = selectFromHalls.executeQuery();
            while (resultSet.next()) {
                System.out.println("Excursion Id : " + resultSet.getInt(1));
                System.out.println("Excursion name : " + resultSet.getString(2));
                System.out.println("=================");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addGuide() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try (PreparedStatement insertToGuide = Database.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO guide(id_position,firstname,lastname)" +
                        "VALUES(?,?,?)")
        ) {
            printPositions();
            System.out.println("Please select id for position");
            int positionId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please input new guide first name");
            String guideFirstName = bufferedReader.readLine();
            System.out.println("Please input new guide last name");
            String guideLastName = bufferedReader.readLine();
            insertToGuide.setInt(1, positionId);
            insertToGuide.setString(2, guideFirstName);
            insertToGuide.setString(3, guideLastName);
            insertToGuide.execute();
            System.out.println("Successfully added new guide: " + guideFirstName + " " + guideLastName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addPosition(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try (PreparedStatement insertToPosition = Database.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO guide_position( position_name)" +
                        "VALUES(?)")
        ) {
            printPositions();
            System.out.println("Please Enter new position");
            String position = bufferedReader.readLine();
            insertToPosition.setString(1, position);
            insertToPosition.execute();
            System.out.println("Successfully added new position: " + position);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePosition() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try (PreparedStatement insertToPosition = Database.getInstance()
                .getConnection()
                .prepareStatement("UPDATE guide_position SET position_name = ? WHERE id_guide_position=?")
        ) {

            printPositions();
            System.out.println("Enter position id for update:");
            int positionId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please Enter position name");
            String position = bufferedReader.readLine();
            insertToPosition.setString(1, position);
            insertToPosition.setInt(2, positionId);
            int rowsAffected = insertToPosition.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated " + rowsAffected + " row");
            } else {
                System.out.println("Nothing was updated");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateGuide(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try (PreparedStatement updateGuide = Database.getInstance()
                .getConnection()
                .prepareStatement("UPDATE guide SET id_position = ?, firstname = ?, lastname = ?  WHERE id_guide=?")
        )
        {
            System.out.println("===================UPDATE GUIDE====================");
            System.out.println("Enter guide id for update:");
            printGuide();
            int guideId = Integer.parseInt(bufferedReader.readLine());
            printPositions();
            System.out.println("Please select id for position");
            int positionId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please input new guide first name");
            String guideFirstName = bufferedReader.readLine();
            System.out.println("Please input new guide last name");
            String guideLastName = bufferedReader.readLine();
            updateGuide.setInt(1, positionId);
            updateGuide.setString(2, guideFirstName);
            updateGuide.setString(3, guideLastName);
            updateGuide.setInt(4, guideId);
            updateGuide.execute();
            System.out.println("Successfully added new guide: " + guideFirstName + " " + guideLastName);
            updateGuide.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteGuide(){

        System.out.println("===================Delete Guide====================");
        try (  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

               PreparedStatement deleteGuide = Database.getInstance()
                       .getConnection()
                       .prepareStatement(" DELETE FROM guide\n" +
                               "WHERE id_guide = ?;")
        ){
            System.out.println("Enter guide for delete:");
            printGuide();
            int guideId = Integer.parseInt(bufferedReader.readLine());
            deleteGuide.setInt(1,guideId);
            deleteGuide.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void findGuidesByPeriod(){
        System.out.println("===================GUIDE's working hours====================");
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try (PreparedStatement getStatisticsForGuide = Database.getInstance()
                .getConnection()
                .prepareStatement("select  g.firstname,g.lastname, count(s.id_excursion)," +
                        " sec_to_time(sum(time_to_sec(s.time_end)-time_to_sec(s.time_start)))from schedules s join guide g on g.id_guide=s.id_guide " +
                        "where s.time_start BETWEEN '2019-08-01 10:00:00' AND '2019-08-01 19:28:00' " +
                        "and  s.time_end BETWEEN '2019-08-01 10:00:00' AND '2019-08-01 19:28:00' " +
                        "group by s.id_guide;")
        ){

            ResultSet resultSet = getStatisticsForGuide.executeQuery();
            while (resultSet.next()) {
                System.out.println("First name : " + resultSet.getString(1));
                System.out.println("Last name : " + resultSet.getString(2));
                System.out.println("Hours of work : " + resultSet.getInt(3));
                System.out.println("=================");
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //'2019-08-01 10:00:00'
    public static void findExcursionsByPeriod(){
        System.out.println("===================Excursions Schedule====================");
        try (PreparedStatement getStatisticsForGuide = Database.getInstance()
                .getConnection()
                .prepareStatement("select e.excursion_name,s.time_start " +
                        "from excursions e  join schedules s on e.id_excursion=s.id_excursion " +
                        "where s.time_start BETWEEN ? AND ?;");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))
        ){

            System.out.println("Enter data when excursion started:");
            System.out.println("Possible date format:\n 2019-12-01 12:00");
            String date_start = bufferedReader.readLine();
            System.out.println("Enter data when excursion ended:");
            String date_end = bufferedReader.readLine();
            getStatisticsForGuide.setString(1,date_start+":00");
            getStatisticsForGuide.setString(2,date_end+":00");
            ResultSet resultSet = getStatisticsForGuide.executeQuery();


            int count=0;
            while (resultSet.next()) {
                count++;
                System.out.println("Excursion name : " + resultSet.getString(1));
                System.out.println("Begins at : " + resultSet.getString(2));
                System.out.println("=================");
            }
            if(count==0) System.out.println("No data found!!!!");

        }catch (SQLException | IOException e) {
            System.out.println("It looks like you entered date wrong...");
        }

    }

    public static void findExcursionQuantityByPeriod(){
        System.out.println("===================Find Excuirsion Statistics====================");
        try (  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

               PreparedStatement excursionStatistics = Database.getInstance()
                       .getConnection()
                       .prepareStatement(
                               "select e.excursion_name, count(s.id_excursion),s.time_start from excursions e join schedules s \n" +
                                       "on s.id_excursion=e.id_excursion where s.time_start BETWEEN ? AND ?\n" +
                                       " and s.time_end BETWEEN ? AND ? group by e.excursion_name;")
        ){

            System.out.println("Enter data when excursion started:");
            System.out.println("Possible date format:\n 2019-12-01 12:00");
            String date_start = bufferedReader.readLine();
            System.out.println("Enter data when excursion ended:");
            String date_end = bufferedReader.readLine();
            excursionStatistics.setString(1,date_start+":00");
            excursionStatistics.setString(2,date_end+":00");
            excursionStatistics.setString(3,date_start+":00");
            excursionStatistics.setString(4,date_end+":00");
            ResultSet resultSet = excursionStatistics.executeQuery();


            int count=0;
            while (resultSet.next()) {
                count++;
                System.out.println("Excursion name : " + resultSet.getString(1));
                System.out.println("Total quantity : " + resultSet.getString(2));
                System.out.println("Time start : " + resultSet.getString(3));
                System.out.println("=================");
            }
            if(count==0) System.out.println("No data found!!!!");
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }






}
