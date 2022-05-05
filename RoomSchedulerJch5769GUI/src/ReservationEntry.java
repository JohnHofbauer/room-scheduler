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
import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author Johnh
 */

// This class holds one entry/row from or for the reservation Table.
public class ReservationEntry {
    //Contructer
    private static Connection connection;
    private static ArrayList<String> Reservations = new ArrayList<String>();
    private static PreparedStatement ReservationEntry;
    private static PreparedStatement Enter;
    
    private String Faculty;
    private String Room;
    private Date Date;
    private int Seats;
    private Timestamp TimeStamp;
    
    public void  ReservationEntry(String Faculty, String Room, Date Date, int Seats, Timestamp TimeStamp) {
        this.Faculty = Faculty;
        this.Room = Room;
        this.Date = Date;
        this.Seats = Seats;
        this.TimeStamp = TimeStamp;  
        
    }
    public String getFaculty() {
        return Faculty;
    }
    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public String getRoom() {
        return Room;
    }
    public void setRoom(String Room) {
        this.Room = Room;
    }

    public Date getDate() {
        return Date;
    }
    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getSeats() {
        return Seats;
    }
    public void setSeats(int Seats) {
        this.Seats = Seats;
    }
    
    public Timestamp getTimeStamp() {
        return TimeStamp;
    }
    public void setTimeStamp(Timestamp TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
}
