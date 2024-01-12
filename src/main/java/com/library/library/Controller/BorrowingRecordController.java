package com.library.library.Controller;
import com.library.library.Service.Interface.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/borrow")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public void borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        borrowingRecordService.borrowBook(bookId, patronId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        borrowingRecordService.returnBook(bookId, patronId);
    }
}