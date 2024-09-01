import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("123", "Book One", "Author One", 2021);
        book2 = new Book("456", "Book Two", "Author Two", 2022);
        library.addBook(book1);
        library.addBook(book2);
    }

    @Test
    public void testAddBook() {
        Book book3 = new Book("789", "Book Three", "Author Three", 2023);
        library.addBook(book3);
        assertTrue(library.viewAvailableBooks().contains(book3));
    }

    @Test
    public void testBorrowBook() throws Exception {
        library.borrowBook("123");
        assertFalse(library.viewAvailableBooks().contains(book1));
    }

    @Test
    public void testReturnBook() throws Exception {
        library.borrowBook("123");
        library.returnBook("123");
        assertTrue(library.viewAvailableBooks().contains(book1));
    }

    @Test
    public void testBorrowBookNotAvailable() {
        assertThrows(Exception.class, () -> library.borrowBook("789"));
    }

    @Test
    public void testReturnBookNotBorrowed() throws Exception {
        library.returnBook("456");
        assertThrows(Exception.class, () -> library.returnBook("456"));
    }

    @Test
    public void testViewAvailableBooks() throws Exception {
        library.borrowBook("123");
        Collection<Book> availableBooks = library.viewAvailableBooks();
        assertTrue(availableBooks.contains(book2));
        assertFalse(availableBooks.contains(book1));
    }
}
