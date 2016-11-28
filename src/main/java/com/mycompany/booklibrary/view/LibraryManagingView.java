/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.view;

import java.util.ArrayList;

/**
 *
 * @author Zakhar
 */
public class LibraryManagingView {
    
    /**
     * outputs received as a method parameter messages to console
     * @param messageArray - array of messages to print
     */
    public void printMessage(ArrayList<String> messageArray){
        if (messageArray == null){
            return;
        }
        for (String message: messageArray){
            System.out.println(message);
        }
    }
    
    /**
     * outputs received as a method parameter messages to console
     * @param messageStr - message to print
     */
    public void printMessage(String messageStr){
        if (messageStr == null){
            return;
        }
        System.out.println(messageStr);
    }
}
