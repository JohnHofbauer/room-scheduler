
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnh
 */
public class WaitlistQueries {
    
    private static Connection connection;
    private static ArrayList<String> rooms = new ArrayList<String>();
    private static PreparedStatement Enter;
    private static PreparedStatement waitlist;
    private static PreparedStatement RemoveWaitlistEntry;
    private static ResultSet resultSet;
    
     public static void addWaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp){
        connection = DBConnection.getConnection();
        try
        {
            Enter = connection.prepareStatement("insert into Waitlist (faculty, date, seats, timestamp) values (?, ?, ?, ?)");
            Enter.setString(1, faculty);
            Enter.setDate(2, date);
            Enter.setInt(3, seats);
            Enter.setTimestamp(4, timestamp);
            Enter.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static ArrayList< ArrayList<String>> getWaitlist(){
        connection = DBConnection.getConnection();
        
        ArrayList< ArrayList<String>> waitList = new ArrayList< ArrayList<String>>();
        ArrayList<String> faculty = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> seats = new ArrayList<String>();
        ArrayList<String> timestamp = new ArrayList<String>();
        try
        {
            waitlist = connection.prepareStatement("select Faculty, date, seats, timestamp from Waitlist order by timestamp");
            resultSet = waitlist.executeQuery();
            
            
            while(resultSet.next())
            {
                 faculty.add(resultSet.getString(1));
                 date.add(resultSet.getString(2));
                 seats.add(resultSet.getString(3));
                 timestamp.add(resultSet.getString(4));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        waitList.add(faculty);
        waitList.add(date);
        waitList.add(seats);
        waitList.add(timestamp);
        
        return waitList;
        
    }
    public static ArrayList< ArrayList<String>> getWaitlistByFaculty(String Faculty){
        connection = DBConnection.getConnection();
        
        ArrayList< ArrayList<String>> waitList = new ArrayList< ArrayList<String>>();
        ArrayList<String> faculty = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> seats = new ArrayList<String>();
        ArrayList<String> timestamp = new ArrayList<String>();
        try
        {
            waitlist = connection.prepareStatement("select Faculty, date, seats, timestamp from Waitlist  Where faculty = ? order by timestamp");
            waitlist.setString(1, Faculty);
            resultSet = waitlist.executeQuery();
            
            
            while(resultSet.next())
            {
                 faculty.add(resultSet.getString(1));
                 date.add(resultSet.getString(2));
                 seats.add(resultSet.getString(3));
                 timestamp.add(resultSet.getString(4));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        waitList.add(faculty);
        waitList.add(date);
        waitList.add(seats);
        waitList.add(timestamp);
        
        return waitList;
        
    }
    public static void RemoveWaitlistEntry(String faculty, Date date) {
         connection = DBConnection.getConnection();
         try
        {
            RemoveWaitlistEntry = connection.prepareStatement("DELETE FROM Waitlist WHERE Faculty = ? and Date = ?");
            RemoveWaitlistEntry.setString(1, faculty);
            RemoveWaitlistEntry.setDate(2, date);
            RemoveWaitlistEntry.executeUpdate();
        }
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
     }
}
