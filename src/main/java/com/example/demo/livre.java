package com.example.demo;

public class livre extends oeuvre {
    private String auteur;

    public livre()
    {
        super();
        this.auteur="";
    }

    public livre(String titre, String auteur){
        super(titre);
        this.auteur=auteur;
    }
    

    public String getAuteur()
    {
        return this.auteur;
    }
}
