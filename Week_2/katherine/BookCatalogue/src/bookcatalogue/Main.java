/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcatalogue;

import java.io.IOException;
import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;


/**
 *
 * @author Katharine
 */
public class Main {
        public static void main(String[] args) throws IOException, ParseException {
        /**
         Things still not done:
         * Edit a book
         * Use Date class to automatically date checkouts, allow sorting by date
         */
        CatalogueManager cat = new CatalogueManager();
        
        DBManager DB = new DBManager();
        
        Scanner scan = new Scanner(System.in);
        
        String filename="bookcatalogue.txt";
        
        boolean foo = true;
        while(foo){
        System.out.println("Enter the filename of an existing library file to work within that library.");
        System.out.println("Enter 'new' to create a new library file.");
        System.out.println("Press enter to work within the default library.");
        
        String in = scan.nextLine();
        
        if(in.equals("new")){
            System.out.println("Enter a filename for the new library:");
            filename = scan.nextLine();
            
            boolean made = DB.makeDB(filename);
            if(made){foo=false;}
            else{filename = "catalogue.txt";};
            }
        else if(!in.equals("")){
            filename = in;
            foo = false;
            }
        else{
            System.out.println("Welcome to Katharine's library!");
            foo=false;
            };
        };
        
        ArrayList<Book> tempDB = DB.openDB(filename);
        
        //System.out.print(tempDB);
        
        
        boolean open = true;
    
        
        while(open){
        
        
        System.out.println("Enter B to borrow/return a book.");
        System.out.println("Enter S to search for books.");
        System.out.println("Enter E to edit book information.");
        System.out.println("Enter V to view all book information.");
        System.out.println("Enter X to exit the library.");
        
        
        String choice = scan.nextLine();
                
        //System.out.println(choice);
        
        if(choice.equalsIgnoreCase("B")){
            System.out.println("Please enter your name:");

            String name = scan.nextLine();
            
            boolean account = true ;
            
            while (account == true){
            cat.printAccount(tempDB, name);
            
            System.out.println("Enter R to return books.");
            System.out.println("Enter B to borrow books.");
            System.out.println("Enter M to return to the main menu.");
            
            String select = scan.nextLine();
            
            if(select.equalsIgnoreCase("R")){
                tempDB = cat.returnBook(tempDB);
                }
            
            else if(select.equalsIgnoreCase("B")){
                System.out.println("What's the date today?");
                String date = scan.nextLine();
                
                tempDB = cat.checkOutBook(tempDB, name, date);
                }
            
            else if(select.equalsIgnoreCase("M")){
                account = false;
                }
            
            else{System.out.println("Not an option.");};
            
            };
            
            
        }
        else if (choice.equalsIgnoreCase("S")){
            System.out.println("Let's find some books!");
            
            cat.printSearch(tempDB);
        }
        else if(choice.equalsIgnoreCase("E")){
            System.out.println("Enter N to create a new book entry.");
            System.out.println("Enter E to edit an existing book entry.");
            System.out.println("Enter D to delete an existing book entry.");
            System.out.println("Enter M to return to the main menu.");

            
           String choice2 = scan.nextLine();
           
           if(choice2.equalsIgnoreCase("N")){
               
               tempDB = cat.createBook(tempDB);
        }
           else if(choice2.equalsIgnoreCase("E")){}
           else if(choice2.equalsIgnoreCase("D")){cat.deleteBook(tempDB);}
           else if(choice2.equalsIgnoreCase("M")){System.out.println("Spoilers!");}
           else{System.out.println("Please enter a choice from the menu above.");};
        }
        else if(choice.equalsIgnoreCase("X")){
            
            DB.writeDB(tempDB, filename);
            
            System.out.println("You are now leaving the library.");
            open = false;
        }
        else if (choice.equalsIgnoreCase("V")){
            cat.printLibrary(tempDB);
            }
        else{
            System.out.println("Please enter B,S, E, or X with no whitespace.");
        }
        };
        
}
}
