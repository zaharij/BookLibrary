/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.controller.command.commands;

import com.mycompany.booklibrary.controller.command.Command;
import static com.mycompany.booklibrary.constants.LibraryControllerConstants.CHOOSE_ONE_USING_CODE_MESSAGE;
import static com.mycompany.booklibrary.constants.LibraryControllerConstants.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RemoveBookCommand
 * @author Zakhar
 */
public class RemoveBookCommand implements Command {
    private ArrayList<String> resultArray = new ArrayList<String>();
    private Pattern commandPattern = Pattern.compile(ID_REG);
    private int bookId;
    private int bookNumber;
    
    /**
     * removes the book from db
     * @param inputedLine user's command
     * @return resultArray
     */
    @Override
    public ArrayList<String> execute(String inputedLine) {
        try{
            bookNumber = Integer.parseInt(inputedLine) - 1;
            Matcher matchetBookNumber = commandPattern.matcher(resultArray.get(bookNumber));
            while (matchetBookNumber.find()) {
                bookId = Integer.parseInt(matchetBookNumber.group());
            }
            if (bookId == 0){
                return null;
            }
            Command.libraryService.removeBook(bookId);
        } catch (Exception ex){
            resultArray = Command.libraryService.removeBook(inputedLine);
            resultArray.add(CHOOSE_ONE_USING_CODE_MESSAGE);
            return resultArray;
        }
        return null;
    }
}