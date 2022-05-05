/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Johnh
 */
public class DatesQueries {
    private static Connection connection;
    private static ArrayList<Date> dates = new ArrayList<Date>();
    private static PreparedStatement getDatesList;
    private static PreparedStatement addDate;
    private static ResultSet resultSet;

    
    public static ArrayList<Date> getDatesList()
    {
        connection = DBConnection.getConnection();
        ArrayList<Date> dates = new ArrayList<Date>();
        try
        {
            getDatesList = connection.prepareStatement("select * from DATES order by date");
            resultSet = getDatesList.executeQuery();
            
            while(resultSet.next())
            {
                dates.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return dates;
        
    }
    public static void addDate(Date date){
        connection = DBConnection.getConnection();
        try
        {
            addDate = connection.prepareStatement("insert into Dates (date) values (?)");
            addDate.setDate(1, date);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
