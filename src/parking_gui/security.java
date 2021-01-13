package Parking_GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class security {
    private static final String USER="root";
    private static final String PASSWORD="";
    private static final String URL="jdbc:mysql://localhost/test11";
    
    static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
