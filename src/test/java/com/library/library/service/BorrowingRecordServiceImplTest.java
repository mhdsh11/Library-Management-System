package com.library.library.service;

import com.library.library.Model.Book;
import com.library.library.Model.BorrowingRecord;
import com.library.library.Model.Patron;
import com.library.library.Repository.BookRepository;
import com.library.library.Repository.BorrowingRecordRepository;
import com.library.library.Repository.PatronRepository;
import com.library.library.Service.Implementation.BorrowingRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingRecordServiceImplTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private BorrowingRecordServiceImpl borrowingRecordService;

    @Mock
    private CacheManager cacheManager;

    @Test
    void testBorrowBook() {
        // Mock data
        Integer bookId = 1;
        Integer patronId = 2;
        Book book = new Book();
        Patron patron = new Patron();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        borrowingRecordService.borrowBook(bookId, patronId);

        verify(bookRepository, times(1)).findById(bookId);
        verify(patronRepository, times(1)).findById(patronId);
        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
        verify(cacheManager.getCache("books"), times(1)).clear();
    }

    @Test
    void testReturnBook() {
        Integer bookId = 1;
        Integer patronId = 2;
        BorrowingRecord borrowingRecord = new BorrowingRecord();

        when(borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId))
                .thenReturn(borrowingRecord);

        borrowingRecordService.returnBook(bookId, patronId);

        verify(borrowingRecordRepository, times(1)).findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
        verify(borrowingRecordRepository, times(1)).save(borrowingRecord);
        verify(cacheManager.getCache("books"), times(1)).evict(any());
    }
}

