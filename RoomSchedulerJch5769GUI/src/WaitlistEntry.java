
import java.sql.Date;
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
public class WaitlistEntry {
    
    private String faculty;
    private Date date;
    private int seats; 
    private Timestamp timestamp;
    
    public  void WaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp){
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
     public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
