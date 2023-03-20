package src.app.entity;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Livre implements Serializable {
    // Définition des attributs
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id_book;

    public String title;
    public String cover;
    public String author;
    
    // Lien avec la table Chapitre
    @OneToMany(
        mappedBy="book", 
        targetEntity=Chapitre.class, 
        cascade=CascadeType.ALL)
    public List<Chapitre> chapters;

    // Constructeurs
    public Livre() {}

    public Livre(String _title, String _author, String _cover) {
        this.id_book = null;
        this.title  = _title;
        this.cover  = _cover;
        this.author = _author;
        this.chapters = new ArrayList<Chapitre>();
    }

    // Getters et Setters
    public Integer getid_book() { return id_book; }
    public String  getTitle()   { return title; }
    public String  getCover()   { return cover; }
    public String  getAuthor()  { return author; }
    public List<Chapitre> getChapters() { return chapters; }

    public void setid_book(Integer _id)   { this.id_book = _id; }
    public void setTitle(String _title)   { this.title = _title; }
    public void setCover(String _cover)   { this.cover = _cover; }
    public void setAuthor(String _author) { this.author = _author; }
    public void setChapters(List<Chapitre> _chapters) { this.chapters = _chapters; }

    // Méthodes
    public String toString() { return this.title+" | "+this.author; }
}