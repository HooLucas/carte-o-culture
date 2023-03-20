package src.app.controller;

public class PageController {
    // Atributs
    private String title;
    private String stylesheet;
    private String body; 

    // Constructeurs
    public PageController() {
        this.title = "";
        this.stylesheet = "";
        this.body = "";
    }

    // Getters et Setters
    public String getTitle()      { return this.title; }
    public String getStylesheet() { return this.stylesheet; }
    public String getBody()       { return this.body; }

    // Méthodes d'ajout d'éléments
    public PageController hasTitle(String s)      { this.title = s; return this; }
    public PageController hasStylesheet(String s) { this.stylesheet = s; return this; }
    public PageController hasBody(String s)       { this.body = s; return this; }

    // Méthodes
    public String build() { return "test"; }    
}
