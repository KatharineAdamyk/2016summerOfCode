package bookcatalogue;

/**
 *
 * @author Katharine
 */
public class Book {
    
    String title;
    String author;
    long isbn;
    String genre;
    boolean onShelf;
    String dateOut;
    String borrower;
    
    
    public Book(String inTitle, String inAuthor, long inIsbn, String inGenre, boolean inStatus, String inDate, String inBorrow){
    title = inTitle;
    author = inAuthor;
    isbn = inIsbn;
    genre = inGenre;
    onShelf = inStatus;
    dateOut = inDate;
    borrower = inBorrow;
    };
}