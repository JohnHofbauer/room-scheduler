
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnh
 */
public class ReservationQueries {
    private static Connection connection;
    private static ArrayList<String> rooms = new ArrayList<String>();
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getReservationsByRoom;
    private static PreparedStatement Enter;
    private static PreparedStatement cancelReservation;
    private static ResultSet resultSet;
    
    public static void getReservations(){
        
    }
    public static ArrayList<String> getRoomsReservedByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getRoomsReservedByDate = connection.prepareStatement("select room from RESERVATIONS where Date = ? order by timestamp");
            getRoomsReservedByDate.setDate(1, date);
            resultSet = getRoomsReservedByDate.executeQuery();
            
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
        
    }
    public static ArrayList< ArrayList<String>> getReservationsByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList< ArrayList<String>> reservations = new ArrayList< ArrayList<String>>();
        ArrayList<String> faculty = new ArrayList<String>();
        ArrayList<String> rooms = new ArrayList<String>();
        ArrayList<String> seats = new ArrayList<String>();
        ArrayList<String> timestamp = new ArrayList<String>();
        try
        {
            //System.out.print(date);
            getReservationsByDate = connection.prepareStatement("select Faculty, room, seats, Timestamp from RESERVATIONS where Date = ? order by timestamp");
            getReservationsByDate.setDate(1, date);
            resultSet = getReservationsByDate.executeQuery();
            
            
            while(resultSet.next())
            {
                 faculty.add(resultSet.getString(1));
                 rooms.add(resultSet.getString(2));
                 seats.add(resultSet.getString(3));
                 timestamp.add(resultSet.getString(4));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        reservations.add(faculty);
        reservations.add(rooms);
        reservations.add(seats);
        reservations.add(timestamp);
        
        return reservations;
        
    }
    public static ArrayList< ArrayList<String>> getReservationsByFaculty(String Faculty){
        connection = DBConnection.getConnection();
        ArrayList< ArrayList<String>> reservations = new ArrayList< ArrayList<String>>();
        ArrayList<String> faculty = new ArrayList<String>();
        ArrayList<String> rooms = new ArrayList<String>();
        ArrayList<String> seats = new ArrayList<String>();
        ArrayList<String> timestamp = new ArrayList<String>();
        try
        {
            //System.out.print(date);
            getReservationsByFaculty = connection.prepareStatement("select Faculty, date, seats, timestamp from RESERVATIONS where faculty = ? order by timestamp");
            getReservationsByFaculty.setString(1, Faculty);
            resultSet = getReservationsByFaculty.executeQuery();
            
            
            while(resultSet.next())
            {
                 faculty.add(resultSet.getString(1));
                 rooms.add(resultSet.getString(2));
                 seats.add(resultSet.getString(3));
                 timestamp.add(resultSet.getString(4));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        reservations.add(faculty);
        reservations.add(rooms);
        reservations.add(seats);
        reservations.add(timestamp);
        
        return reservations;
        
    }
    public static ArrayList< ArrayList<String>> getReservationsByRoom(String roomName){
        connection = DBConnection.getConnection();
        ArrayList< ArrayList<String>> reservations = new ArrayList< ArrayList<String>>();
        ArrayList<String> faculty = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> seats = new ArrayList<String>();
        ArrayList<String> timestamp = new ArrayList<String>();
        try
        {
            //System.out.print(date);
            getReservationsByRoom = connection.prepareStatement("select Faculty, date, seats, timestamp from RESERVATIONS where room = ? order by timestamp");
            getReservationsByRoom.setString(1, roomName);
            resultSet = getReservationsByRoom.executeQuery();
            
            
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
        reservations.add(faculty);
        reservations.add(date);
        reservations.add(seats);
        reservations.add(timestamp);
        
        return reservations;
        
    }
    public static void addReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp){
        connection = DBConnection.getConnection();
        try
        {
            Enter = connection.prepareStatement("insert into Reservations (faculty, room, date, seats, timestamp) values (?, ?, ?, ?, ?)");
            Enter.setString(1, faculty);
            Enter.setString(2, room);
            Enter.setDate(3, date);
            Enter.setInt(4, seats);
            Enter.setTimestamp(5, timestamp);
            Enter.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void cancelReservation(String faculty, Date date) {
         connection = DBConnection.getConnection();
         try
        {
            cancelReservation = connection.prepareStatement("DELETE FROM Reservations WHERE Faculty = ? and Date = ?");
            cancelReservation.setString(1, faculty);
            cancelReservation.setDate(2, date);
            cancelReservation.executeUpdate();
        }
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
     }
    public static  String getRoomByFacultyandDate(String faculty, Date date){
        
        connection = DBConnection.getConnection();
        String roomName = null;
        
        try
        {
            //System.out.print(date);
            getReservationsByDate = connection.prepareStatement("select room from RESERVATIONS WHERE Faculty = ? and Date = ? order by timestamp");
            //System.out.println("Faculty: " + faculty + " and Date: " + date);
            getReservationsByDate.setString(1, faculty);
            getReservationsByDate.setDate(2, date);
            resultSet = getReservationsByDate.executeQuery();
            
            
            while(resultSet.next())
            {
                roomName = resultSet.getString(1);
            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        // Exception if the reservation is not found.
        if (roomName ==  null) {
            System.out.println("ACTION: getRoomByFacultyandDate <=> Cannot be proformd for <roomName> is null");
            return null;
        }
        
        return roomName ;
    }
}