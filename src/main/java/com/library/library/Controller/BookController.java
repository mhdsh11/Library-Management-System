package com.library.library.Controller;

import com.library.library.Model.Book;
import com.library.library.Service.Interface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}