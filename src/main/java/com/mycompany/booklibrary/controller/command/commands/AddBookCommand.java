/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.controller.command.commands;

import com.mycompany.booklibrary.controller.command.Command;
import java.util.ArrayList;

/**
 *AddBookCommand
 * @author Zakhar
 */
public class AddBookCommand implements Command {

    /**
     * inserts the book to the db
     * @param inputedLine user's command
     * @return  null
     */
    @Override
    public ArrayList<String> execute(String inputedLine) {
        Command.libraryService.addBook(inputedLine);
        return null;
    }
    
}
