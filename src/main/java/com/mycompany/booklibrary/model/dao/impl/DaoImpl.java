/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.model.dao.impl;

import com.mycompany.booklibrary.model.dao.connection.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mycompany.booklibrary.constants.LibraryControllerConstants.*;

/**
 * DaoImpl
 * @author Zakhar
 */
public class DaoImpl {
    private PreparedStatement preparedStatement;
    private final static String ADD_BOOK_SQL = "INSERT INTO library (author, book) VALUES (?,?);";
    private final static int ADD_BOOK_SQL_BOOK_NUMBER = 1;
    private final static int ADD_BOOK_SQL_AUTHOR_NUMBER = 2;
    private final static int DELETE_BOOK_SQL_VALUE_NUMBER = 1;
    private final static String DELETE_BOOK_BY_ID_SQL = "DELETE FROM library WHERE id=?";
    private final static String DELETE_BOOK_BY_NAME_SQL = "DELETE FROM library WHERE book=?";
    private final static String SELECT_BOOK_SQL = "SELECT * FROM library WHERE book = ?";
    private final static int SELECT_BOOK_SQL_NUMBER = 1;
    private final static String SELECT_ALL_BOOK_SQL = "SELECT * FROM library";
    public final static String EDIT_BOOK_SQL = "UPDATE library SET book = ? WHERE id = ?";
    private final static int EDIT_BOOK_NAME_NUMBER = 1;
    private final static int EDIT_BOOK_ID_NUMBER = 2;
    private ResultSet resultSet;
    private ArrayList<String> resultSearchArray = new ArrayList<String>();
    private int connectionErrorCount = DEFAULT_COUNT_VALUE;
    
    /**
     * inserts new book to the db
     * first inserts the author, and then the book
     */
    public void addBookToDb(String book, String author){
        if (connectionErrorCount > MAX_COUNT_VALUE){
            return;
        }
        try (Connection connection = ConnectionMySQL.getConnection();){
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(ADD_BOOK_SQL);
                preparedStatement.setString(ADD_BOOK_SQL_BOOK_NUMBER, book);
                preparedStatement.setString(ADD_BOOK_SQL_AUTHOR_NUMBER, author);
                preparedStatement.executeUpdate();
                connection.commit();
                connectionErrorCount = DEFAULT_COUNT_VALUE;
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            connectionErrorCount++;
            addBookToDb(book, author);
        }
    }
    
    /**
     * inserts new book to the db
     * first inserts the author, and then the book
     */
    public void editBook(String book, int id){
        if (connectionErrorCount > MAX_COUNT_VALUE){
            return;
        }
        try (Connection connection = ConnectionMySQL.getConnection();){
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(EDIT_BOOK_SQL);
                preparedStatement.setString(EDIT_BOOK_NAME_NUMBER, book);
                preparedStatement.setInt(EDIT_BOOK_ID_NUMBER, id);
                preparedStatement.executeUpdate();
                connection.commit();
                connectionErrorCount = DEFAULT_COUNT_VALUE;
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            connectionErrorCount++;
            editBook(book, id);
        }
    }
    
    /**
     * removes book from db by it's id
     * @param bookId - book's id
     */
    public void removeBookFromDb(int bookid){
        if (connectionErrorCount > MAX_COUNT_VALUE){
            return;
        }
        try (Connection connection = ConnectionMySQL.getConnection();){
            try {
                preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_ID_SQL);
                preparedStatement.setInt(DELETE_BOOK_SQL_VALUE_NUMBER, bookid);
                preparedStatement.executeUpdate();
                connectionErrorCount = DEFAULT_COUNT_VALUE;
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            connectionErrorCount++;
            removeBookFromDb(bookid);
        }
    }
    
    /**
     * removes book from db by it's name
     * @param book - book name
     */
    public void removeBookFromDb(String book){
        if (connectionErrorCount > MAX_COUNT_VALUE){
            return;
        }
        try (Connection connection = ConnectionMySQL.getConnection();){
            try {
                preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_NAME_SQL);
                preparedStatement.setString(DELETE_BOOK_SQL_VALUE_NUMBER, book);
                preparedStatement.executeUpdate();
                connectionErrorCount = DEFAULT_COUNT_VALUE;
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            connectionErrorCount++;
            removeBookFromDb(book);
        }
    }

    /**
     * returns all books by given name
     * @param bookName
     */
    public ArrayList<String> select(String bookName){
        if (connectionErrorCount > MAX_COUNT_VALUE){
            return null;
        }
        Integer bookNumber = START_ELEMENT_VALUE;
        try (Connection connection = ConnectionMySQL.getConnection();){
            try {
                preparedStatement = connection.prepareStatement(SELECT_BOOK_SQL);
                preparedStatement.setString(SELECT_BOOK_SQL_NUMBER, bookName);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    resultSearchArray.add(bookNumber.toString().concat(WHITESPACE).concat(resultSet.getString(BOOK)).concat(WHITESPACE)
                            .concat(resultSet.getString(AUTHOR)).concat(CODE).concat(resultSet.getString(ID)));
                    bookNumber++;
                }
                connectionErrorCount = DEFAULT_COUNT_VALUE;
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            connectionErrorCount++;
            select(bookName);
        }
        return resultSearchArray;
    }
    
    /**
     * returns all books
     */
    public ArrayList<String> selectAll(){
        if (connectionErrorCount > MAX_COUNT_VALUE){
            return null;
        }
        try (Connection connection = ConnectionMySQL.getConnection();){
            try {
                boolean qwe = connection.isClosed();
                preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK_SQL);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    resultSearchArray.add(resultSet.getString(BOOK).concat(WHITESPACE)
                            .concat(resultSet.getString(AUTHOR)).concat(CODE).concat(resultSet.getString(ID)));
                }
                connectionErrorCount = DEFAULT_COUNT_VALUE;
            } catch (SQLException ex) {
                Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            connectionErrorCount++;
            selectAll();
        }
        return resultSearchArray;
    }
}
