package com.library.library.Service.Interface;


import com.library.library.Model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Integer id);

    void addBook(Book book);

    void updateBook(Integer id, Book book);

    void deleteBook(Integer id);
}