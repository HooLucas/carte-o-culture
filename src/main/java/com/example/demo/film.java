package com.example.demo;

public class film extends oeuvre {
    private String producteur;
    private lieu tournage;

    public film()
    {
        super();
        this.producteur="";
        this.tournage=null;
    }

    public film(String producteur)
    {
        super();
        this.producteur=producteur;
        this.tournage=null;
    }

    public film(String producteur,lieu tournage)
    {
        super();
        this.producteur=producteur;
        this.tournage=tournage;
    }

    public String getProducteur()
    {
        return this.producteur;
    }

    public lieu getTournage()
    {
        return this.tournage;
    }
}
