/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.constants;

import com.mycompany.booklibrary.controller.command.Command;
import com.mycompany.booklibrary.controller.command.commands.*;
import java.util.HashMap;
import java.util.Map;

/**
 * program constants
 * @author Zakhar
 */
public class LibraryControllerConstants {
    public final static String COMMAND_REG = "^[a-zA-Z\\s.]+[^:]";
    public final static String AUTHOR_REG = "(?<=:\\s)[a-zA-Z\\s.]+[^\\s\"]";
    public final static String BOOK_REG = "(?<=\")[a-zA-Z\\s.]+";
    public final static String ID_REG = "(?<=code:)\\d+";
    public final static String BOOK_NUMBER_REG = "^[\\d]+";
    
    public final static String ADD_BOOK_COMMAND = "add";
    public final static String REMOVE_BOOK_COMMAND = "remove";
    public final static String EDIT_BOOK_COMMAND = "edit";
    public final static String ALL_BOOKS_COMMAND = "all books";
    public final static String END_COMMAND = "end";
    
    public final static String BOOK = "book";
    public final static String AUTHOR = "author";
    public final static String CODE = " | code:";
    public final static String ID = "id";
    public final static String WHITESPACE = " ";
    
    public final static int DEFAULT_COUNT_VALUE = 0;
    public final static int MAX_COUNT_VALUE = 1;
    public final static int START_ELEMENT_VALUE = 1;
    
    public final static String NO_SUCH_COMMAND_MESSAGE = "No such command!";
    public final static String WAS_DELETED_MESSAGE = "the book was deleted succesfully.";
    public final static String WAS_EDITED_MESSAGE = "the book was edited succesfully.";
    public final static String CHOOSE_ONE_USING_CODE_MESSAGE = "choose one, using the number:";
    
    public final static Map<String, Command> COMMAND_MAP = new HashMap<String, Command>()//existing commands
    {{
        put(ADD_BOOK_COMMAND, new AddBookCommand());
        put(REMOVE_BOOK_COMMAND, new RemoveBookCommand());
        put(EDIT_BOOK_COMMAND, new EditBookCommand());
        put(ALL_BOOKS_COMMAND, new AllBooksCommand());
        put(END_COMMAND, new EndCommand());
    }};
}
