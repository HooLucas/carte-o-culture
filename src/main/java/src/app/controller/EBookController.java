package src.app.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import nl.siegmann.epublib.epub.*;
import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.domain.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import src.app.entity.Page;
import src.app.models.Ebook;
import src.app.entity.Livre;
import src.app.entity.Chapitre;


@Controller
public class EBookController {
    // Attributs
    Ebook file;

    // Constructeurs
    public EBookController() {
        this.file = new Ebook();
    }

    public EBookController(Model m) {
        this.file = new Ebook();
        m.addAttribute("ebook", this.file);
    }

    // Getters et Setters
    public Ebook getFile() {
        return this.file;
    }

    public void setFile(Ebook _file) {
        this.file = _file;
    }

    // Méthodes

    // Chargement des données depuis un fichier local
    public Livre loadDataFromFile(String filepath) {
        Livre temp = new Livre();
        try {
            FileInputStream is = new FileInputStream(filepath);
            EpubReader reader = new EpubReader();
            Book book = reader.readEpub(is);   
                     
            String title  = getTitle(book);
            String author = getAuthor(book); 
            String cover  = getCoverImage(book);

            temp = new Livre(title,author,cover);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    // Chargement des données depuis un fichier epub (ebook)
    public Livre loadDataFromEbook(Ebook ebook) {
        Livre temp = new Livre();
        try {
            MultipartFile file = ebook.getFile();
            InputStream is = file.getInputStream();
            EpubReader reader = new EpubReader();
            Book book = reader.readEpub(is); 

            temp = parseDataJSON(book);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    // Parsing des données d'un livre en données JSON
    public Livre parseDataJSON(Book livre) {
        String title  = getTitle(livre);
        String author = getAuthor(livre); 
        String cover  = getCoverImage(livre);
        Livre book= new Livre(title, author, cover);

        List<Chapitre> chapters = getChapters(livre);
        ArrayList<ArrayList<Page>> pages = getPages(livre);

        for(int i=0; i < chapters.size(); i++) {
            Chapitre chapter = chapters.get(i);
            chapter.setbook(book);
            List<Page> page = pages.get(i);
            for(Page p : page) {    
                p.setchapter(chapter);
            }
            chapter.setpages(page);
        }
        book.setChapters(chapters);

        return book;
    }

    // Recupere le titre d'un ebook
    public String getTitle(Book livre) {
       return livre.getMetadata().getTitles().get(0);
    }

    // Recupere l'auteur d'un ebook
    public String getAuthor(Book livre) {
        String firstname = livre.getMetadata().getAuthors().get(0).getFirstname();
        String lastname  = livre.getMetadata().getAuthors().get(0).getLastname();
        return (firstname+" "+lastname);
    }

    // Recupere la date de publication d'un ebook
    public Date getDate(Book livre) {
        return livre.getMetadata().getDates().get(0);
    }

    // Recupere la date de publication d'un ebook
    public List<String> getDescription(Book livre) {
        return livre.getMetadata().getDescriptions();
    }

    // Recupere l'image de couverture d'un ebook
    public String getCoverImage(Book livre) {
        String filename = "temp.jpg";
        try {
            String filepath = "./src/main/resources/static/img/cover/"+filename;
            File file = new File(filepath);
            InputStream is = livre.getCoverImage().getInputStream();
            BufferedImage buffer = ImageIO.read(is);
            ImageIO.write(buffer, "jpg", file);
        } catch (IOException e) {
            System.out.println("Cant save cover image");
        }
        return filename;
    }

    // Parcourt le texte du livre
    public ArrayList<ArrayList<Page>> getPages(Book livre) {
        ArrayList<ArrayList<Page>> temp = new ArrayList<ArrayList<Page>>(); Integer i=1; 
        String line="";
        String texte="";
        try {
            nl.siegmann.epublib.domain.Spine spine = livre.getSpine();
            List<Resource> sommaire = livre.getTableOfContents().getAllUniqueResources(); 
            sommaire = sommaire
                .stream()
                .filter(s -> s.getId().contains("main"))
                .collect(Collectors.toList());
                ;
            
            for(Resource chapitre : sommaire) {
                InputStream is = chapitre.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"UTF-8");
                BufferedReader bf = new BufferedReader(isr);

                line=""; texte="";
                while((line = bf.readLine()) != null ) {
                    texte += line;
                }
                Document document  = Jsoup.parse(texte, "UTF-8");
                texte = document.body().text();

                Page p = new Page(i,texte);
                ArrayList<Page> pages = new ArrayList<Page>();
                pages.add(p);
                temp.add(pages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    // Recupere la liste des chapitres du livre
    public List<Chapitre> getChapters(Book livre) {
        ArrayList<Chapitre> chapitres = new ArrayList<Chapitre>(); Integer i=1;
        try {
            List<Resource> sommaire = livre.getTableOfContents().getAllUniqueResources(); 
            sommaire = sommaire
                .stream()
                .filter(s -> s.getId().contains("main"))
                .collect(Collectors.toList());
                ;
            
            for(Resource chapitre : sommaire) {
                InputStream is = chapitre.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"UTF-8");
                BufferedReader bf = new BufferedReader(isr);

                chapitres.add(new Chapitre("Chapitre "+i)); i+=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chapitres;
    }

    // Recupere la liste des lieux cités dans le livre
    public List<String> getLieux(String text) {
        text  = text.replace(","," ")
                    .replace(";"," ")
                    .replace(","," ")
                    .replace("."," ")
                    .replace("!"," ")
                    .replace("?"," ")
                    .replace(")"," ")
                    .replace("("," ")
                    .replace("'"," ")
            ;
        List<String> result = new ArrayList<String>();
        for(String mot : Arrays.asList(text.split(" "))) {
            if(mot.matches("[A-Z][a-z]*")) {
                result.add(mot);
            }
        }
        return result;
    }  
}
