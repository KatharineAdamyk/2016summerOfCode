package bookcatalogue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Katharine
 */
public class CatalogueManager {
    
    public ArrayList<Book> createBook(ArrayList<Book> array) throws IOException{
        //Ideally this method would compare the ISBN and/or title to the already existing books
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the book's title.");
        String inTitle = scan.nextLine();
        
        System.out.println("Enter the book's author.");
        String inAuthor = scan.nextLine();
        
        Scanner scanInt = new Scanner(System.in);
        System.out.println("Enter the book's ISBN number.");
        long inIsbn = scanInt.nextLong();
      
        System.out.println("Enter the book's genre.");
        String inGenre = scan.nextLine();
        
        boolean inStatus = true;
        String inDate = "never";
        String inBorrower = "nobody";
        
        Book name = new Book(inTitle, inAuthor, inIsbn, inGenre, inStatus, inDate, inBorrower);
        
        array.add(name);
        
        return array;
        
    }
    
   
    public ArrayList<Book> deleteBook(ArrayList<Book> tempDB){
            System.out.println("First we need to find the book you want to delete:");
            System.out.println("Enter A to search by author, title, or genre.");
            System.out.println("Enter B to find titles checked out by an individual.");
            System.out.println("Enter I to search by ISBN.");
            System.out.println("Enter M to return to the main menu.");
            
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();
            
            if(choice.equalsIgnoreCase("A")){
                System.out.println("Please enter a character string you would like to search for:");
                
                String search = scan.nextLine();
                
                ArrayList<Book> results = findBook(tempDB, search);
                return delete(tempDB, results);
                }
            
            else if(choice.equalsIgnoreCase("I")){
                System.out.println("Please enter the ISBN you would like to search for:");
                Scanner scanInt = new Scanner(System.in);
                int search = scanInt.nextInt();
                
                ArrayList<Book> results = findIsbn(tempDB, search);
                return delete(tempDB, results);
            }
            else if(choice.equalsIgnoreCase("B")){
                System.out.println("Please enter the name of the borrower you would like to search for:");
                
                String searchb = scan.nextLine();

                ArrayList<Book> results = findBorrower(tempDB, searchb);
                return delete(tempDB, results);
                    }
                
             else if(choice.equalsIgnoreCase("M")){ System.out.println("Allons y!"); return tempDB;}
             else{System.out.println("Not a choice!"); return tempDB;}
    }               
             
    public ArrayList<Book> delete(ArrayList<Book> tempDB, ArrayList<Book> results){
        int length = results.size();
        if(length==0){System.out.println("There's no book in the library with this information.");}
        else if(length==1){System.out.println("Is this the book you would like to delete? (Y/N)");
            Book deletedBook=results.get(0);
            System.out.print(deletedBook.title + " by "+deletedBook.author+", "+deletedBook.genre +"\n");
            
            Scanner scan = new Scanner(System.in);
            String yn = scan.next();
            
            if(yn.equalsIgnoreCase("Y")){
                tempDB.remove(deletedBook);
                }
            else{
                System.out.println("Try again!");
                };
            }
        else{System.out.println("More than one book matches that information. \n Enter the number next to the book you wish to delete:");
            for(int j=0; j<length; j++){
                System.out.print( j +".) " + results.get(j).title + " by "+results.get(j).author+", "+results.get(j).genre +"\n");
                };
                    
            Scanner scanInt = new Scanner(System.in);
            int dchoice = scanInt.nextInt();
                    
            if(0<= dchoice && dchoice<length){
                Book deletedBook=results.get(dchoice);
                tempDB.remove(deletedBook);
                }
            else{
                System.out.println("Try again and follow the directions this time!");
                };
        }
    return tempDB;
};
    
    public ArrayList<Book> findBook(ArrayList<Book> array, String string){
        ArrayList<Book> results = new ArrayList<Book>();
        for(Book book: array){
            if(book.title.toLowerCase().contains(string.toLowerCase()) | book.author.toLowerCase().contains(string.toLowerCase()) | book.genre.toLowerCase().contains(string.toLowerCase())  ){
            results.add(book);};
            };
        return results;
    };
    
    public ArrayList<Book> findIsbn(ArrayList<Book> array, int search){
        ArrayList<Book> results = new ArrayList<Book>();
        for(Book book: array){
            if(book.isbn == search){
            results.add(book);};
            };
        return results;
    }  

    public ArrayList<Book> findBorrower(ArrayList<Book> array, String string){
        ArrayList<Book> results = new ArrayList<Book>();
        for(Book book: array){
            if(book.borrower.toLowerCase().contains(string.toLowerCase()) ){
            results.add(book);};
            };
        return results;
    };

    public void printSearch(ArrayList<Book> tempDB){
            System.out.println("Enter A to search by author, title, or genre.");
            System.out.println("Enter B to find titles checked out by an individual.");
            System.out.println("Enter I to search by ISBN.");
            System.out.println("Enter M to return to the main menu.");
            
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();
            
            if(choice.equalsIgnoreCase("A")){
                System.out.println("Please enter a character string you would like to search for:");
                
                String search = scan.nextLine();
                
                ArrayList<Book> results = findBook(tempDB, search);
                
                int length = results.size();
                if(length==0){System.out.println("Your search terms don't match any books in the library.");}
                else if(length==1){System.out.println("One book matches your search terms:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    if(book.onShelf){System.out.print("This book is currently on the shelf.\n");}
                    else{System.out.print("This book has been checked out by "+book.borrower+" since "+book.dateOut+".\n");};
                    };}
                else{System.out.println("The following " + length + " books match your search terms:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    if(book.onShelf){System.out.print("This book is currently on the shelf.\n");}
                    else{System.out.print("This book has been checked out by "+book.borrower+" since "+book.dateOut+".\n");};
                    };
                }
            }
            else if(choice.equalsIgnoreCase("I")){
                System.out.println("Please enter the ISBN you would like to search for:");
                
                int search = scan.nextInt();
                
                ArrayList<Book> results = findIsbn(tempDB, search);
                
                int length = results.size();
                if(length==0){System.out.println("Your search terms don't match any books in the library.");}
                else if(length==1){System.out.println("This book has the ISBN you searched for:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    if(book.onShelf){System.out.print("This book is currently on the shelf.\n");}
                    else{System.out.print("This book has been checked out by "+book.borrower+" since "+book.dateOut+".\n");};
                    };}
                else{System.out.println("More than one book has that ISBN (you might want to look into that:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    if(book.onShelf){System.out.print("This book is currently on the shelf.\n");}
                    else{System.out.print("This book has been checked out by "+book.borrower+" since "+book.dateOut+".\n");};
                    };
                    }
            }
            else if(choice.equalsIgnoreCase("B")){
                System.out.println("Please enter the name of the borrower you would like to search for:");
                
                String searchb = scan.nextLine();
                
                ArrayList<Book> results = findBorrower(tempDB, searchb);
                
                int length = results.size();
                if(length==0){System.out.println(searchb + " has no books currently checked out.");}
                else if(length==1){System.out.println(searchb + " currently has one book checked out:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    System.out.print("Checked out on: "+book.dateOut);
                    };}
                else{System.out.println(searchb + " currently has" + length +" books checked out:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    System.out.print("Checked out on: "+book.dateOut);
                    };
                    }
                }
            else if(choice.equalsIgnoreCase("M")){ System.out.println("Allons y!"); }
            else{System.out.println("Please enter a choice from the menu above.");};
            
    };
    
    public void printAccount(ArrayList<Book> tempDB, String searcher){
                ArrayList<Book> results = findBorrower(tempDB, searcher);
                
                int length = results.size();
                if(length==0){System.out.println("You have no books currently checked out.\n");}
                else if(length==1){System.out.println("You currently have one book checked out:");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    System.out.print("Checked out on: "+book.dateOut +"\n");
                    };}
                else{System.out.print("You currently have " + length +" books checked out: \n");
                    for(Book book : results){
                    System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    System.out.print("Checked out on: "+book.dateOut +"\n");
                    };
                    }
            
    };
    
    public void printLibrary(ArrayList<Book> tempDB){
        for (Book book : tempDB){
        System.out.print(book.title + " by "+book.author+", "+book.genre +" ISBN:"+book.isbn+"\n");
                    if(book.onShelf){System.out.print("This book is currently on the shelf.\n");}
                    else{System.out.print("This book has been checked out by "+book.borrower+" since "+book.dateOut+".\n");}
        }
        };
    
    
    public ArrayList<Book> checkOutBook(ArrayList tempDB, String name, String date){
            Scanner scan = new Scanner(System.in);
            System.out.println("Now let's find a book you want to check out:");
            
            boolean checkingOut = true;
            
            while(checkingOut == true){
            System.out.println("Enter A to search by author, title, or genre.");
            //System.out.println("Enter B to find titles checked out by an individual.");
            System.out.println("Enter I to search by ISBN.");
            System.out.println("Enter M to return to your account menu.");
            
            
            String choice = scan.nextLine();
            
            if(choice.equalsIgnoreCase("A")){
                System.out.println("Please enter a character string you would like to search for:");
                
                String search = scan.nextLine();
                
                ArrayList<Book> results = findBook(tempDB, search);
                tempDB = checkOut(tempDB, results, name, date);
                
                }
            
            else if(choice.equalsIgnoreCase("I")){
                System.out.println("Please enter the ISBN you would like to search for:");
                Scanner scanInt = new Scanner(System.in);
                int search = scanInt.nextInt();
                
                ArrayList<Book> results = findIsbn(tempDB, search);
                tempDB = checkOut(tempDB, results, name, date);
            }
            else if(choice.equalsIgnoreCase("B")){
                System.out.println("Please enter the name of the borrower you would like to search for:");
                
                String searchb = scan.nextLine();

                ArrayList<Book> results = findBorrower(tempDB, searchb);
                tempDB = checkOut(tempDB, results, name, date);
                    }
                
             else if(choice.equalsIgnoreCase("M")){ System.out.println("Allons y!"); checkingOut = false;}
             else{System.out.println("Not a choice!"); }
            
            }
        return tempDB;
        };
    
    public ArrayList<Book> checkOut(ArrayList<Book> tempDB, ArrayList<Book> results, String name, String date){
        int length = results.size();
        if(length==0){System.out.println("There's no book in the library with this information.");}
        else if(length==1){System.out.println("Is this the book you would like to check out? (Y/N)");
            Book outBook=results.get(0);
            System.out.print(outBook.title + " by "+outBook.author+", "+outBook.genre +"\n");
            
            Scanner scan = new Scanner(System.in);
            String yn = scan.next();
            
            if(yn.equalsIgnoreCase("Y")){
                outBook.onShelf = false;
                outBook.borrower = name;
                outBook.dateOut = date;
                System.out.println(outBook.title + " has been checked out. \n You can search for more books to check out now:");
                }
            else{
                System.out.println("Try again!");
                };
            }
        else{System.out.println("More than one book matches this information. \n Enter the number next to the book you wish to check out:");
            for(int j=0; j<length; j++){
                System.out.print( j +".) " + results.get(j).title + " by "+results.get(j).author+", "+results.get(j).genre +"\n");
                };
                    
            Scanner scanInt = new Scanner(System.in);
            int dchoice = scanInt.nextInt();
                    
            if(0<= dchoice && dchoice<length){
                Book outBook=results.get(dchoice);
                outBook.onShelf = false;
                outBook.borrower = name;
                outBook.dateOut = date;
                System.out.println(outBook.title + " has been checked out. \n You can search for more books to check out now:");
                }
            else{
                System.out.println("Try again and follow the directions this time!");
                };
        }
        
        return tempDB;
        };
    
    public ArrayList<Book> returnBook(ArrayList tempDB){
        System.out.println("Use the list of your checkouts above to find and enter the ISBN of a book you want to return:");
        Scanner scanInt = new Scanner(System.in);
        
                int returnIsbn = scanInt.nextInt();

                ArrayList<Book> results = findIsbn(tempDB, returnIsbn);
                
                int length = results.size();
                if(length==0){System.out.println("There's no book in the library with this ISBN.");}
                else if(length==1){System.out.println("Is this the book you would like to return? (Y/N)");
                    Book returnedBook=results.get(0);
                    System.out.print(returnedBook.title + " by "+returnedBook.author+", "+returnedBook.genre +"\n");
            
                    Scanner scan = new Scanner(System.in);
                    String yn = scan.next();
            
                        if(yn.equalsIgnoreCase("Y")){
                            returnedBook.onShelf = true;
                            returnedBook.borrower = "nobody";
                            returnedBook.dateOut = "never";
                            }
                        else{
                            System.out.println("Try again!");
                        };
                    }
                else{System.out.println("More than one book has that ISBN (you might want to look into that). \n Enter the number next to the book you wish to check out:");
                    for(int j=0; j<length; j++){
                    System.out.print(j + ".) " + results.get(j).title + " by "+results.get(j).author+", "+results.get(j).genre +"\n");
                    };
                   
                    int rchoice = scanInt.nextInt();
                    
                    if(0<= rchoice && rchoice<length){
                        Book returnedBook=results.get(rchoice);
                        returnedBook.onShelf = true;
                        returnedBook.borrower = "nobody";
                        returnedBook.dateOut = "never";
                        }
                    else{System.out.println("Try again and follow the directions this time!");};
                    
                    }
        
        return tempDB;
        };
    
}


