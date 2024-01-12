package com.library.library.Service.Interface;


import com.library.library.Model.Patron;

import java.util.List;

public interface PatronService {
    List<Patron> getAllPatrons();

    Patron getPatronById(Integer id);

    void addPatron(Patron patron);

    void updatePatron(Integer id, Patron updatedPatron);

    void deletePatron(Integer id);
}