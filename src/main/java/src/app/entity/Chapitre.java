package src.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chapitre implements Serializable {
    // Attributs
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id_chapter;

    // Lien avec la table Livre
    @ManyToOne(targetEntity=Livre.class)
    @JoinColumn(name="id_book")
    @JsonIgnore
    public Livre book;

    // Lien avec la table Page
    @OneToMany(
        mappedBy="chapitre", 
        targetEntity=Page.class, 
        cascade=CascadeType.ALL)
    @JsonIgnore
    public List<Page> pages;

    public String nom;    

    // Constructeurs
    public Chapitre() {}

    public Chapitre(String _nom) {
        this.id_chapter = null;
        this.nom        = _nom;
    }

    // Getters et Setters
    public void setnom(String _nom) {this.nom = _nom;}
    public void setbook(Livre _book) { this.book = _book; }
    public void setpages(List<Page> _pages) {this.pages = _pages;}
    public void setid_chapter(Integer _id) { this.id_chapter = _id; }

    public String  getnom() {return this.nom;}
    public Livre   getbook() { return this.book; }
    public List<Page> getpages() { return this.pages;}
    public Integer getid_chapter() { return id_chapter; }

    // Définition des méthodes
    public String toString() { return this.id_chapter+" | "+this.nom;}
}