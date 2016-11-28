/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.model.impl;

import static com.mycompany.booklibrary.constants.LibraryControllerConstants.*;
import com.mycompany.booklibrary.model.dao.impl.DaoImpl;
import com.mycompany.booklibrary.model.entities.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LibraryService
 * @author Zakhar
 */
public class LibraryService {
    private Pattern authorPattern = Pattern.compile(AUTHOR_REG);
    private Pattern bookPattern = Pattern.compile(BOOK_REG);
    private ArrayList<String> resultArray = new ArrayList<String>();
    private Pattern bookNamePattern = Pattern.compile(BOOK_REG);
    private DaoImpl daoImpl = new DaoImpl();
    private Book book;
    
    /**
     * implements command "add"
     * adds new book and it's author to the db
     * @param inputedLine user's inputed line
     */
    public void addBook(String inputedLine){
        Matcher matcherAuthorCommand = authorPattern.matcher(inputedLine);
        while (matcherAuthorCommand.find()) {
            book.setAuthor(matcherAuthorCommand.group());
        }
        Matcher matcherBookCommand = bookPattern.matcher(inputedLine);
        while (matcherBookCommand.find()) {
            book.setBookName(matcherBookCommand.group());
        }
        daoImpl.addBookToDb(book.getAuthor(), book.getBookName());
    }
    
    /**
     * selected all books with same name to edit
     * @param bookName - book name
     */
    public ArrayList<String> removeBook(String inputedLine){
        Matcher matchetBookName = bookNamePattern.matcher(inputedLine);
        while (matchetBookName.find()) {
            book.setBookName(matchetBookName.group());
        }
        resultArray = daoImpl.select(book.getBookName());
        return resultArray;
    }
    
    /**
     * removes book from db by it's id
     * @param bookId - book id
     */
    public String removeBook(int bookId){
        book.setBookId(bookId);
        daoImpl.removeBookFromDb(book.getBookId());
        return WAS_DELETED_MESSAGE;
    }
    
    /**
     * selected all books with same name to edit
     * @param inputedLine - inputed line
     */
    public ArrayList<String> editBook(String inputedLine){
        Matcher matchetBookName = bookNamePattern.matcher(inputedLine);
        while (matchetBookName.find()) {
            book.setBookName(matchetBookName.group());
        }
        resultArray = daoImpl.select(book.getBookName());
        return resultArray;
    }
    
    /**
     * updates book name
     * @param bookId - book id
     * @param bookNameNew - new name
     */
    public String editBook(String bookNameNew, int bookId){
        book.setBookName(bookNameNew);
        book.setBookId(bookId);
        daoImpl.editBook(book.getBookName(), book.getBookId());
        return WAS_EDITED_MESSAGE;
    }
    
    /**
     * selects all books, ordered by the name
     * @param bookId - book id
     */
    public ArrayList<String> selectAll(){
        return sortArray(daoImpl.selectAll());
    }
    
    /**
     * sorts the array
     * @return 
     */
    public ArrayList<String> sortArray(ArrayList<String> array){
        resultArray = array;
        Collections.sort(resultArray);
        return resultArray;
    } 
}
