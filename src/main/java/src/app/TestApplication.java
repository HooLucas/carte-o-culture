package src.app;

import src.app.controller.*;
import src.app.entity.*;

public class TestApplication {
    public static void main(String[] args) {
        String filepath = "./data/ebook/alain-fournier-le-grand-meaulnes.epub";
        EBookController control = new EBookController();
        Livre l = control.loadDataFromFile(filepath);
     }    
}
