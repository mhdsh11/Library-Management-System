package com.library.library.Service.Implementation;

import com.library.library.Model.Book;
import com.library.library.Model.BorrowingRecord;
import com.library.library.Model.Patron;
import com.library.library.Repository.BookRepository;
import com.library.library.Repository.BorrowingRecordRepository;
import com.library.library.Repository.PatronRepository;
import com.library.library.Service.Interface.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public void borrowBook(Integer bookId, Integer patronId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Patron patron = patronRepository.findById(patronId).orElse(null);

        if (book != null && patron != null) {
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setBookId(book.getId());
            borrowingRecord.setPatron(patron);
            borrowingRecord.setPatronId(patron.getId());
            borrowingRecord.setBorrowingDate(LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "{#bookId, #patronId}")
    public void returnBook(Integer bookId, Integer patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
        if (borrowingRecord != null) {
            borrowingRecord.setReturnDate(LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);
        }
    }
}