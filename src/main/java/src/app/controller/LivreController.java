package src.app.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import src.app.entity.Livre;
import src.app.service.LivreService;

@RestController
public class LivreController {
    // Atributs
    @Autowired
    private LivreService service;

    // RESTful API methods for READ operations
    @GetMapping("/api/livres/")
    public ResponseEntity<List<Livre>> getAll() {
        try {
            List<Livre> livres = this.service.getAll();
            return new ResponseEntity<List<Livre>>(livres, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Livre>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/livres/{id}")
    public ResponseEntity<Livre> get(@PathVariable Integer id) {
        try {
            Livre livre = this.service.get(id);
            return new ResponseEntity<Livre>(livre, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Livre>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for CREATE operation
    @PostMapping("/api/livres/add")
    public Integer add(@RequestBody Livre livre) {
        return this.service.save(livre).getid_book();
    }

    // RESTful API method for UPDATE operation
    @PutMapping("/api/livres/{id}")
    public ResponseEntity<?> update(@RequestBody Livre livre, @PathVariable Integer id) {
        try {
            Livre existProduct = this.service.get(id);
            this.service.save(livre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for DELETE operation
    @DeleteMapping("/api/livres/{id}")
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }
}