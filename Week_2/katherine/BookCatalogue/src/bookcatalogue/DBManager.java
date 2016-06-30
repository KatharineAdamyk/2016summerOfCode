package bookcatalogue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Katharine
 */
public class DBManager {
    
    public boolean makeDB(String filename){
        try{    
            File file = new File(filename);
            return file.createNewFile();
            
        }
        catch(Exception e){
         e.printStackTrace();
         return false;
        }
        };
    
    public ArrayList<Book> openDB(String filename) throws FileNotFoundException, ParseException{
        ArrayList<Book> tempDB = new ArrayList();
        
            Scanner input = new Scanner(new File(filename)).useDelimiter(",\\s*");
            //DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
       
            while(input.hasNext()) {
                long isbn = input.nextLong();
                String title = input.next();
                String author = input.next();
                String genre = input.next();
                boolean status = input.nextBoolean();
                String dateOut = input.next();
                String borrower = input.next();
                
                Book tempBook = new Book(title, author, isbn, genre, status, dateOut, borrower);
                tempDB.add(tempBook);
        
    }
              return tempDB;
}
    
    public void writeDB(ArrayList<Book> array, String filename) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for(Book book : array){
                writer.print(book.isbn +", "+ book.title + ", " + book.author + ", " + book.genre + ", "+ book.onShelf + ", " + book.dateOut + ", " + book.borrower +"," );
            };
            writer.close();
    }
}
