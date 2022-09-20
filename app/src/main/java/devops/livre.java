package devops;

public class livre extends oeuvre {
    private String auteur;

    public livre()
    {
        super();
        this.auteur="";
    }

    public livre(String auteur){
        super();
        this.auteur=auteur;
    }

    public String getAuteur()
    {
        return this.auteur;
    }
}
