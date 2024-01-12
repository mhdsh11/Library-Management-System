package com.library.library.service;
import com.library.library.Model.Patron;
import com.library.library.Repository.PatronRepository;
import com.library.library.Service.Implementation.PatronServiceImpl;
import com.library.library.Service.Interface.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatronServiceImplTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronServiceImpl patronService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPatrons_ReturnsListOfPatrons() {
        List<Patron> expectedPatrons = Arrays.asList(new Patron(), new Patron());
        when(patronRepository.findAll()).thenReturn(expectedPatrons);

        List<Patron> actualPatrons = patronService.getAllPatrons();

        assertEquals(expectedPatrons, actualPatrons);
        verify(patronRepository, times(1)).findAll();
    }

    @Test
    void getPatronById_WithValidId_ReturnsPatron() {
        Integer patronId = 1;
        Patron expectedPatron = new Patron();
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(expectedPatron));

        Patron actualPatron = patronService.getPatronById(patronId);

        assertEquals(expectedPatron, actualPatron);
        verify(patronRepository, times(1)).findById(patronId);
    }

    @Test
    void addPatron_WithValidPatron_SavesPatron() {
        Patron patronToAdd = new Patron();
        patronService.addPatron(patronToAdd);
        verify(patronRepository, times(1)).save(patronToAdd);
    }

    @Test
    void updatePatron_WithValidIdAndUpdatedPatron_UpdatesPatron() {
        Integer patronId = 1;
        Patron existingPatron = new Patron();
        Patron updatedPatron = new Patron();

        when(patronRepository.findById(patronId)).thenReturn(Optional.of(existingPatron));
        patronService.updatePatron(patronId, updatedPatron);
        verify(patronRepository, times(1)).save(existingPatron);
        assertEquals(updatedPatron.getName(), existingPatron.getName());
        assertEquals(updatedPatron.getContactInformation(), existingPatron.getContactInformation());
    }

    @Test
    void deletePatron_WithValidId_DeletesPatron() {
        Integer patronId = 1;
        patronService.deletePatron(patronId);
        verify(patronRepository, times(1)).deleteById(patronId);
    }
}
