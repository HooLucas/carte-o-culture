package src.app.service;

import src.app.entity.Livre;
import src.app.repository.LivreRepository;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class LivreService {
    // Attributs
    @Autowired private LivreRepository repo;

    // Définition des méthodes CRUD

    // Définition des méthodes CREATE
    public Livre save(Livre livre) {
        return this.repo.save(livre);
    }

    // Définition des méthodes READ
    public List<Livre> getAll() {
        return this.repo.findAll();
    }

    public Livre get(Integer id) {
        return this.repo.findById(id).get();
    }
    
    // Définition des méthodes UPDATE

    // Définition des méthodes DELETE
    public void delete(Integer id) {
        this.repo.deleteById(id);
    }
}