/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnh
 */
public class RoomEntry {
    
    private String roomName;
    private int seats;
    
    public void RoomEntry(String roomName, int seats){
        this.roomName = roomName;
        this.seats = seats;
                
    }

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    
            
}
