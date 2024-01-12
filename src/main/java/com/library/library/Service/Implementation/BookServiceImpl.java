package com.library.library.Service.Implementation;

import com.library.library.Service.Interface.BookService;
import com.library.library.Model.Book;
import com.library.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public void updateBook(Integer id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            bookRepository.save(book);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
