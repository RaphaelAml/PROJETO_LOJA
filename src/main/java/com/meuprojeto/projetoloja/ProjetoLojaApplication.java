package com.meuprojeto.projetoloja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.meuprojeto.model")
@ComponentScan(basePackages = {"com.meuprojeto.*"})
@EnableJpaRepositories({"com.meuprojeto.repository"})
@EnableTransactionManagement
public class ProjetoLojaApplication {

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("123"));

        SpringApplication.run(ProjetoLojaApplication.class, args);

    }
    
}



