/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.model.dao.impl;

import java.util.ArrayList;

/**
 *
 * @author Zakhar
 */
public interface LibraryDAOInterface {
    public void addBookToDb(String book, String author);
    public void editBook(String book, int id);
    public void removeBookFromDb(int bookid);
    public ArrayList<String> select(String bookName);
    public ArrayList<String> selectAll();
}
