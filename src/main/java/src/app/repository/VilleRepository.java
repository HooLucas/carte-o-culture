package src.app.repository;

import java.util.List;
import src.app.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
    List<Ville> findByNom(String name);
}