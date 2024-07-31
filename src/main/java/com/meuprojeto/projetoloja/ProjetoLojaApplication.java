package com.meuprojeto.projetoloja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.meuprojeto.model")
@ComponentScan(basePackages = {"com.meuprojeto.*"})
@EnableJpaRepositories({"com.meuprojeto.repository"})
@EnableTransactionManagement
public class ProjetoLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoLojaApplication.class, args);

    }
    
}



