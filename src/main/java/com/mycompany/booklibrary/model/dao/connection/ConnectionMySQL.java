/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.model.dao.connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConnectionMySQL
 * connection settings
 * @author Zakhar
 */
public class ConnectionMySQL {
    private final static String CONNECTION_STRING = "jdbc:mysql://localhost:3306/booklibrary";
    private final static String USER_NAME = "Zakhar";
    private final static String PASSWORD_DB = "98818467Chryst";
    private static Connection connection;
    
    /**
     * creates the connection
     */
    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD_DB);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
