package com.meuprojeto.projetoloja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.meuprojeto.projetoloja.model")
public class ProjetoLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoLojaApplication.class, args);

    }
    
}



