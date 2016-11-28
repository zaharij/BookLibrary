/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.controller.command;

import com.mycompany.booklibrary.model.impl.LibraryService;
import java.util.ArrayList;

/**
 * interface Command
 * @author Zakhar
 */
public interface Command {
    public static LibraryService libraryService = new LibraryService();
    
    /**
     * overriding method, which implements command execution
     * @param inputedLine
     * @return 
     */
    public ArrayList<String> execute(String inputedLine);
}
