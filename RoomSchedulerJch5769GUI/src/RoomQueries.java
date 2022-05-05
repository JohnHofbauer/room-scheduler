/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Johnh
 */
public class RoomQueries {
    private static Connection connection;
    private static ArrayList<String> rooms = new ArrayList<String>();
    private static PreparedStatement getAllRooms;
    private static PreparedStatement addRooms;
    private static PreparedStatement dropRoom;
    private static ResultSet resultSet;
    
    public static ArrayList<String> getAllRooms() {
        
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getAllRooms = connection.prepareStatement("select name from Rooms order by name");
            resultSet = getAllRooms.executeQuery();
            
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
    public static ArrayList<String> getRoomsBiggerThan(int seats) {
        
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getAllRooms = connection.prepareStatement("select name, seats from Rooms where seats > ? order by seats ");
            getAllRooms.setInt(1, seats-1);
            resultSet = getAllRooms.executeQuery();
            
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
    public static Integer getseatsWithRoomNameAs(String roomName) {
        
        connection = DBConnection.getConnection();
        int Seats = 0;
        
        try
        {
            getAllRooms = connection.prepareStatement("select seats from Rooms where Name = ?");
            getAllRooms.setString(1, roomName);
            resultSet = getAllRooms.executeQuery();
            
            while(resultSet.next())
            {               
                Seats = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Seats;
    }
     public static void addRoom(String Name, int Seats) {
         connection = DBConnection.getConnection();
         try
        {
            addRooms = connection.prepareStatement("insert into Rooms (Name, Seats) values (?, ?)");
            addRooms.setString(1, Name);
            addRooms.setInt(2, Seats);
            addRooms.executeUpdate();
        }
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
     }
     public static void dropRoom(String name) {
         connection = DBConnection.getConnection();
         try
        {
            dropRoom = connection.prepareStatement("DELETE FROM Rooms WHERE Name = ?");
            dropRoom.setString(1, name);
            dropRoom.executeUpdate();
        }
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
     }
}
