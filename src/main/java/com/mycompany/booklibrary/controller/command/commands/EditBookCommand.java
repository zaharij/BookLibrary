/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.controller.command.commands;

import static com.mycompany.booklibrary.constants.LibraryControllerConstants.*;
import com.mycompany.booklibrary.controller.command.Command;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zakhar
 */
public class EditBookCommand implements Command{
    private ArrayList<String> resultArray = new ArrayList<String>();
    private Pattern commandPattern = Pattern.compile(ID_REG);
    private Pattern bookNamePattern = Pattern.compile(BOOK_REG);
    private Pattern bookNumberPattern = Pattern.compile(BOOK_NUMBER_REG);
    private int bookId;
    private int bookNumber;
    private String newName;
    private String currentBook;
    
    /**
     * renames the book
     * @param inputedLine user's command
     * @return result of book search
     */
    @Override
    public ArrayList<String> execute(String inputedLine) {
        try{
            Matcher mBookNumber = bookNumberPattern.matcher(inputedLine);
            while (mBookNumber.find()) {
                bookNumber = Integer.parseInt(mBookNumber.group()) - 1;
            }
            currentBook = resultArray.get(bookNumber);
            Matcher matchetBookNumber = commandPattern.matcher(currentBook);
            while (matchetBookNumber.find()) {
                bookId = Integer.parseInt(matchetBookNumber.group());
            }
            if (bookId == 0){
                return null;
            }
            Matcher matchetBookNameNumber = bookNamePattern.matcher(inputedLine);
            while (matchetBookNameNumber.find()) {
                newName = matchetBookNameNumber.group();
            }
            Command.libraryService.editBook(newName, bookId);
        } catch (Exception ex){
            resultArray = Command.libraryService.removeBook(inputedLine);
            resultArray.add(CHOOSE_ONE_USING_CODE_MESSAGE);
            return resultArray;
        }
        return null;
    }
    
}
