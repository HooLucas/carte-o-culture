package src.app.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import src.app.entity.Page;
import src.app.repository.PageRepository;

@Service
@Transactional
public class PageService {
    // Attribut
    @Autowired private PageRepository repo;

    // Définition des méthodes CRUD

    // Définition des méthodes CREATE
    public Page save(Page page) {
        return this.repo.save(page);
    }

    // Définition des méthodes READ
    public List<Page> getAll() {
        return this.repo.findAll();
    }

    public Page get(Integer id) {
        return this.repo.findById(id).get();
    }
    
    // Définition des méthodes UPDATE

    // Définition des méthodes DELETE
}