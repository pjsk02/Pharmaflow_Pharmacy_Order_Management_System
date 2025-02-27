/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import constant_package.Database_Details;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author deepthiramesh
 */
public abstract class Connection {
    public static java.sql.Connection con = null;
    public static java.sql.Connection getConnection(){
        if (con!=null) {
            return con;
        }
        else {
            connect();
            return con;
        }
    }
    public static void connect() {
        try {
            con = DriverManager.getConnection(
                Database_Details.getConnectionUrl(),
                Database_Details.getUser(),
                Database_Details.getPassword()
            );
        } catch (SQLException e) {
            System.out.println("Unable to connect to MySql: " + e);
        }
    }
    
    public static boolean closeConnection(java.sql.Connection con) {
        boolean isClosed = false;
        try {
            con.close();
            isClosed = true;
            return isClosed;
        } catch (SQLException e) {
            return isClosed;
        }
    } 
}
