package com.example.demo;

import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @GetMapping("/")
	public String home() {
		String home = "<h1> Projet Carte-O-Culture </h1><h2>Demo</h2><a href='map.html'>Lien Demo</a>";
		return home;
	}

	@GetMapping("/map")
    public String map(){
		Livre test = new Livre("les fleurs du mal","Baudelaire");
        return "map";
    }
}
