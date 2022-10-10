package devops;

public class musique extends oeuvre {
    private String artiste;

    public musique()
    {
        super();
        this.artiste="";
    }

    public musique(String nom){
        super();
        this.artiste=nom;
    }

    public String getArtiste()
    {
        return this.artiste;
    }
}
