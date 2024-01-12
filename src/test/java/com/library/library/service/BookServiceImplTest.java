package com.library.library.service;
import com.library.library.Model.Book;
import com.library.library.Repository.BookRepository;
import com.library.library.Service.Implementation.BookServiceImpl;
import com.library.library.Service.Interface.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooksTest() {
        List<Book> mockBooks = Arrays.asList(
                new Book( "Book1", "Author1", 2022, "ISBN123"),
                new Book( "Book2", "Author2", 2023, "ISBN456")
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);
        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookByIdTest() {
        Book mockBook = new Book( "Book1", "Author1", 2022, "ISBN123");

        when(bookRepository.findById(1)).thenReturn(Optional.of(mockBook));
        Book result = bookService.getBookById(1);
        assertEquals("Book1", result.getTitle());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void addBookTest() {
        Book newBook = new Book("book1", "author1", 1998, "0011");
        bookService.addBook(newBook);
        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    void updateBookTest() {
        Book existingBook = new Book("Book1", "Author1", 2022, "ISBN123");
        Book updatedBook = new Book( "UpdatedBook", "UpdatedAuthor", 2025, "ISBNXYZ");
        when(bookRepository.findById(1)).thenReturn(Optional.of(existingBook));

        bookService.updateBook(1, updatedBook);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void deleteBookTest() {
        bookService.deleteBook(1);
        verify(bookRepository, times(1)).deleteById(1);
    }
}
