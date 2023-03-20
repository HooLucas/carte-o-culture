package src.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import src.app.entity.Ville;
import src.app.service.VilleService;

 @RestController
 public class VilleController {
    // Atributes
    @Autowired private VilleService service;

    // RESTful API methods for READ operations
    @GetMapping("/api/villes/")
    public ResponseEntity<List<Ville>> getAll() {
        try {
            List<Ville> villes = this.service.getAll();
            return new ResponseEntity<List<Ville>>(villes, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Ville>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/ville/{id}")
    public ResponseEntity<Ville> get(@PathVariable Integer id) {
        try {
            Ville ville = this.service.get(id);
            return new ResponseEntity<Ville>(ville, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Ville>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/ville/{name}")
    public ResponseEntity<List<Ville>> getFiltered(@PathVariable List<String> names) {
        try {
            List<Ville> villes = this.service.getFiltered(names);
            return new ResponseEntity<List<Ville>>(villes, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Ville>>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for CREATE operation
    @PostMapping("/api/villes/add")
    public Integer addNewVille (@RequestParam String nom, @RequestParam Float x, @RequestParam Float y) {
        Ville v = new Ville();
        v.setNom(nom);
        v.setLatitude(x);
        v.setLongitude(y);
        return this.service.save(v).getID();
    }

    // RESTful API method for UPDATE operation
    
    // RESTful API method for DELETE operation

}
