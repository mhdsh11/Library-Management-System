package com.library.library.Service.Interface;
import com.library.library.Model.BorrowingRecord;

public interface BorrowingRecordService {
    void borrowBook(Integer bookId, Integer patronId);

    void returnBook(Integer bookId, Integer patronId);
}
