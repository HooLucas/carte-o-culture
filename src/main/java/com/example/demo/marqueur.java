package com.example.demo;

public class marqueur {
    private oeuvre oeuvre;
    private lieu lieu;

    public marqueur()
    {
        this.oeuvre=null;
        this.lieu=null;
    }

    public marqueur(oeuvre oeuvre)
    {
        this.oeuvre=oeuvre;
        this.lieu=null;
    }

    public marqueur(lieu lieu)
    {
        this.oeuvre=null;
        this.lieu=lieu;
    }

    public marqueur(oeuvre oeuvre, lieu lieu)
    {
        this.oeuvre=oeuvre;
        this.lieu=lieu;
    }

    public oeuvre getOeuvre()
    {
        return this.oeuvre;
    }

    public lieu getLieu()
    {
        return this.lieu;
    }
}
