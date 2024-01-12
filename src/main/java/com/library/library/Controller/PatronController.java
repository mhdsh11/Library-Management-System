package com.library.library.Controller;
import com.library.library.Model.Patron;
import com.library.library.Service.Interface.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable Integer id) {
        return patronService.getPatronById(id);
    }

    @PostMapping
    public void addPatron(@RequestBody Patron patron) {
        patronService.addPatron(patron);
    }

    @PutMapping("/{id}")
    public void updatePatron(@PathVariable Integer id, @RequestBody Patron updatedPatron) {
        patronService.updatePatron(id, updatedPatron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable Integer id) {
        patronService.deletePatron(id);
    }
}