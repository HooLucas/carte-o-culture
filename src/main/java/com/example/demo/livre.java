package com.example.demo;

public class Livre extends oeuvre {
    private String auteur;

    public Livre()
    {
        super();
        this.auteur="";
    }

    public Livre(String titre, String auteur){
        super(titre);
        this.auteur=auteur;
    }
    

    public String getAuteur()
    {
        return this.auteur;
    }
}
