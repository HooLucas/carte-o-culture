package src.app.controller;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;

import org.springframework.stereotype.Controller;
import src.app.models.Lieux;

@Controller
public class CarteController {
    // Atributs
    ArrayList<Lieux> lieux;

    // Methodes

    // Recupere une liste de toute les villes de France
    public ArrayList<Lieux> getVilleFrance() {
        ArrayList<Lieux> tab = new ArrayList<Lieux>();
        try {
            String filepath = "lib/ville_data/data_geo_france.json";
            FileReader f = new FileReader(filepath);
            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(f);
            JSONArray array = new JSONArray();
            array.add(obj);        
            for (int i = 0; i < array.size(); i++) {
                JSONObject ville = (JSONObject) array.get(i);
            /*
                String name = (String) ville.get("name");           
                Double x = (Double) ville.get("gps_x");           
                Double y  = (Double) ville.get("gps_y");
                Lieux l = new Lieux(name, 0.0, 0.0);               
                System.out.println(l.toString());
            */  
            }                   
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tab;
    }

    // Recupere une liste de lieux à partir d'un texte
    public String getLieux(String texte) {
        String lieux = "";
        texte = texte.replace(","," ")
            .replace(";"," ")
            .replace(","," ")
            .replace("."," ")
            .replace("!"," ")
            .replace("?"," ")
            .replace(")"," ")
            .replace("("," ")
            .replace("'"," ")
            ;
        Set<String> result = new HashSet<String>();
        for(String mot : Arrays.asList(texte.split(" "))) {
            if(mot.matches("[A-Z][a-z]*")) {
                result.add(mot);
            }
        }
        System.out.println(result);        
        ArrayList<Lieux> villes_france = getVilleFrance();
        return lieux;
    }        
}
