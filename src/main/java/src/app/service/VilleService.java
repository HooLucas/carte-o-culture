package src.app.service;

import java.util.List;
import java.util.ArrayList;
import javax.transaction.Transactional;

import src.app.entity.Ville;
import src.app.repository.VilleRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class VilleService {

    @Autowired private VilleRepository repo;

    // Définition des méthodes CRUD

    // Définition des méthodes CREATE
    public Ville save(Ville ville) {
        return this.repo.save(ville);
    }

    // Définition des méthodes READ
    public List<Ville> getAll() {
        return this.repo.findAll();
    }

    public Ville get(Integer id) {
        return this.repo.findById(id).get();
    }

    public List<Ville> getFiltered(List<String> names) {
        List<Ville> villes = new ArrayList<Ville>();
        for(String nom : names) {
            List<Ville> temp = this.repo.findByNom(nom);
            if(temp.size() > 0) {
                for(Ville ville : temp) {
                    villes.add(ville);
                }
            }
        }
        return villes;
    }
    
    // Définition des méthodes UPDATE

    // Définition des méthodes DELETE
}