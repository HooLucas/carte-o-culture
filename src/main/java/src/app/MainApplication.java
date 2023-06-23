package src.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.*;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import src.app.entity.Livre;
import src.app.models.Ebook;
import src.app.controller.*;

@Controller
@SpringBootApplication
public class MainApplication {
	// Controllers
	@Autowired 
	private LivreController livreAPI;
	
	@Autowired 
	private VilleController villeAPI;	
	
	private CarteController carteAPI;
	private EBookController ebookAPI = new EBookController();
	private PageController  pageBUILDER = new PageController();
	
	// Lancement de l'application
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	// Rendement des pages

	// Rendement de la page d'acceuil
	@GetMapping("")
	public String getIndex(Model m) {
		return "index";
	}

	// Rendement des pages de livre
	@GetMapping("livres")
	public String getLivreAll(Model m) {
		try {
			m.addAttribute("livres", livreAPI.getAll().getBody());
			return "book/book-display-all";
		} catch (Exception e) {
			return "error";
		}
    }

	// Rendemement de la page d'information d'un livre
	@GetMapping("livres/{id}")
	public String getLivreById(Model m, @PathVariable Integer id) {
		try {
			m.addAttribute("livre", livreAPI.get(id).getBody());
			return "book/book-display-info";
		} catch (Exception e) {
            return "error";
        }
    }

	// Rendemement de la page de lecture d'un livre
	@GetMapping("livres/{id}/read")
	public String readLivre(Model m, @PathVariable Integer id) {
		try {
			m.addAttribute("livre", livreAPI.get(id).getBody());
			return "book/book-read";
		} catch (Exception e) {
			return "error";
		}
	}

	// Rendemement de la page d'ajout d'un livre
	@GetMapping("livres/add")
	public String addLivreForm(Model m) {
		m.addAttribute("ebook", new EBookController());
		return "book/book-form-add";
    }

	// Traitement de la requète d'ajout d'un livre
	@PostMapping("livres/add")
	public String addLivreRequest(@ModelAttribute Ebook ebook) {
		try {
			if(ebook.isEmpty() == false) {
				System.out.println("Nouveau Livre Détecté");
				Livre l = ebookAPI.loadDataFromEbook(ebook);
				//List<String> names = new ArrayList<String>(Arrays.asList(l.getTexte().split(","))); 
				//System.out.println(this.villeAPI.getFiltered(names));
				Integer new_id = livreAPI.add(l);
				
				Path path1 = Paths.get("./src/main/resources/static/img/cover/temp.jpg");
				Path path2 = Paths.get("./src/main/resources/static/img/cover/"+new_id+".jpg");
				if(new_id > 0) {
					Files.move(path1, path2, REPLACE_EXISTING);
				} else {
					Files.delete(path1);
				}	
				System.out.println("Nouveau Livre Ajouté");
				return("redirect:/livres");	
			} else {
				System.out.println("Aucun Livre Envoyé..");
				return("redirect:/livres/add"); 		
			}       
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}


	// Rendement de la page de carte
	@GetMapping("carte")
	public String getCarte(Model model) {
		model.addAttribute("carte", carteAPI);
		model.addAttribute("villes", villeAPI.getAll().getBody());
		model.addAttribute("livres", livreAPI.getAll().getBody());
        return("map/map-display");
    }

	// Rendement de la page de test
	@GetMapping("test")
	public String getTest(Model m) {
		m.addAttribute("livre", livreAPI.get(0));
		m.addAttribute("page_content", pageBUILDER);
		return pageBUILDER.hasBody("<div th:replace='book/book-display-all::copy'></div>").build();
		//!\\ bloque au render du thymeleaf du builder //!\\
	}
}
