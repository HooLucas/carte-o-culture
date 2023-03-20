package src.app.models;

import org.springframework.web.multipart.MultipartFile;

public class Ebook {
    // Attributs
    String name;
    MultipartFile file;

    // Constructeurs
    public Ebook() {
        this.name = null;
        this.file = null;
    }

    // Getters et Setters
    public String getName() { return this.name; }
    public MultipartFile getFile() { return this.file; }

    public void setName(String s) { this.name = s; }    
    public void setFile(MultipartFile f) { this.file = f; }    

    // Méthodes
    public boolean isEmpty() {
        if(this.file.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}