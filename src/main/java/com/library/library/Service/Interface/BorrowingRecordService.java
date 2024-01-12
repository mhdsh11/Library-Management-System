package com.library.library.Service.Interface;

public interface BorrowingRecordService {
    void borrowBook(Integer bookId, Integer patronId);

    void returnBook(Integer bookId, Integer patronId);
}
