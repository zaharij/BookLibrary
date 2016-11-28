package com.mycompany.booklibrary.controller;

import java.util.Scanner;
import java.util.regex.Pattern;
import static com.mycompany.booklibrary.constants.LibraryControllerConstants.*;
import com.mycompany.booklibrary.controller.command.Command;
import com.mycompany.booklibrary.controller.command.CommandFactory;
import com.mycompany.booklibrary.view.LibraryManagingView;
import java.util.regex.Matcher;

/**
 *
 * @author Zakhar
 */
public class LibraryManagingController {
    private String inputedLine = null;
    private String inputedCommand = null;    
    private Pattern commandPattern = Pattern.compile(COMMAND_REG);
    private CommandFactory commandFactory = new CommandFactory();
    private Command command = null;
    private LibraryManagingView libraryManagingView = new LibraryManagingView();
    
    /**
     * implements relations between model and view
     * - receives inputed line
     * - finds and calls the command
     * - works till the user inputs the command "end" (ignore case)
     */
    public void startManaging(){
        do {
            Scanner scanIn = new Scanner(System.in);
            inputedLine = scanIn.nextLine();
            Matcher matchetCommand = commandPattern.matcher(inputedLine.trim());
            while (matchetCommand.find()) {
                inputedCommand = matchetCommand.group().toLowerCase();
            }
            command = commandFactory.getCommand(inputedCommand);
            if(command != null){
                libraryManagingView.printMessage(command.execute(inputedLine));
            } else {
                libraryManagingView.printMessage(NO_SUCH_COMMAND_MESSAGE);
            }
        } while(!END_COMMAND.equals(inputedCommand));
    }
}
