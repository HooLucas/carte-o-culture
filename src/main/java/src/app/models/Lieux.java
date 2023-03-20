package src.app.models;

public class Lieux {
    // Définition des attributs
    private String ville;
    private double x;
    private double y;

    // Définitions des constructeurs
    public Lieux() {
        this.ville = "?";
        this.x = 0;
        this.y = 0;
    }

    public Lieux(String _ville, double _x, double _y) {
        this.ville= _ville;
        this.x = _x;
        this.y = _y;
    }
    
    // Définition des getters et setters
    public String getVille() {
        return this.ville;
    }

    public void setVille(String _ville) {
        this.ville = _ville;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double _x) {
        this.x = _x; 
    }

    public double getY() {
        return this.y;
    }

    public void setY(double _y) {
        this.y = _y; 
    }

    // Définition des méthodes
    public String toString() {
        return this.ville+" "+this.x+" "+this.y;
    }
}