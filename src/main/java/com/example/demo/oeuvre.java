package com.example.demo;

public class oeuvre {
    protected String titre;

    public oeuvre()
    {
        this.titre="JE SUIS UNE OEUVRE";
    }

    public oeuvre(String titre)
    {
        this.titre=titre;
    }

    public String getTitre()
    {
        return this.titre;
    }
}
