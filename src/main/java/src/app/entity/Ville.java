package src.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ville")
public class Ville {
    // Attributs
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String nom;
    private Float latitude;
    private Float longitude;

    // Constructeurs
    public Ville() {}

    public Ville(String _nom, Float _lat, Float _long) {
        this.id  = null;
        this.nom = _nom;
        this.latitude  = _lat;
        this.longitude = _long;
    }

    // Getters et Setters 
    public Integer getID() { return this.id; }
    public String  getNom() { return this.nom; }
    public Float   getLatitude() { return this.latitude; }
    public Float   getLongitude() { return this.longitude; }

    public void setNom(String s) { this.nom=s; }
    public void setLatitude(Float x)  { this.latitude=x; }
    public void setLongitude(Float y) { this.longitude=y; }

    // Méthodes
    public String toString() { return this.nom; }
}