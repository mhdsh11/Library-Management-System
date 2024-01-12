package com.library.library.Service.Implementation;


import com.library.library.Model.Patron;
import com.library.library.Repository.PatronRepository;
import com.library.library.Service.Interface.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable("patrons")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "patrons", key = "#id")
    public Patron getPatronById(Integer id) {
        return patronRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", allEntries = true)
    public void addPatron(Patron patron) {
        patronRepository.save(patron);
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public void updatePatron(Integer id, Patron updatedPatron) {
        Optional<Patron> existingPatron = patronRepository.findById(id);
        if (existingPatron.isPresent()) {
            Patron patron = existingPatron.get();
            patron.setName(updatedPatron.getName());
            patron.setContactInformation(updatedPatron.getContactInformation());
            patronRepository.save(patron);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Integer id) {
        patronRepository.deleteById(id);
    }
}