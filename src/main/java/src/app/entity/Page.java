package src.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="page")
public class Page {
    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id_page;

    @ManyToOne(targetEntity=Chapitre.class)
    @JoinColumn(name="id_chapter")
    @JsonIgnore
    public Chapitre chapitre;

    public Integer numero;
    public String  texte;

    // Constructeurs
    public Page() {}

    public Page(Integer _numero, String _texte) {
        this.id_page = null;
        this.numero  = _numero;
        this.texte   = _texte;
    }

    // Getters et Setters
    public void setId(Integer _id) { this.id_page = _id; }
    public void setnumero(Integer _numero) { this.numero = _numero; }
    public void settexte(String _texte) { this.texte = _texte; }
    public void setchapter(Chapitre _chapitre) { this.chapitre = _chapitre; }

    public Integer  getId() { return id_page; }
    public Integer  getnumero() { return this.numero; }
    public String   gettexte() { return this.texte; }
    public Chapitre getchapter() { return this.chapitre; }

    // Méthodes
    public String toString() { return this.texte; }
}
