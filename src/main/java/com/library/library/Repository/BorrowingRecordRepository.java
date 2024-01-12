package com.library.library.Repository;

import com.library.library.Model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    BorrowingRecord findByBookIdAndPatronIdAndReturnDateIsNull(Integer bookId,Integer patronId);
}