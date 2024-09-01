import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Library {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void borrowBook(String isbn) throws Exception {
        Book book = books.get(isbn);
        if (book == null) {
            throw new Exception("Book not found.");
        }
        if (!book.isAvailable()) {
            throw new Exception("Book is not available.");
        }
        book.setAvailable(false);
    }

    public void returnBook(String isbn) throws Exception {
        Book book = books.get(isbn);
        if (book == null) {
            throw new Exception("Book not found.");
        }
        if (book.isAvailable()) {
            throw new Exception("Book was not borrowed.");
        }
        book.setAvailable(true);
    }

    public Collection<Book> viewAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .toList();
    }
}
