package com.meuprojeto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/*Cria a autenticacao e retorna tambem a autenticacao JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {

    /*Token de validade de 11 dias*/
    private static final long EXPIRATION_TIME = 959990000;

    /*Chave de senha para juntar com JWT*/
    private static final String SECRET = "Abacaxi com limao";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    /*Gera o token e a resposta para o cliente com o JWT*/
    public void addAuthentication(HttpServletResponse response,String username) throws Exception{

        /*Montagem do token*/

        String JWT = Jwts.builder() /*Chama o gerador de token*/
                .setSubject(username) /*Adiciona o user*/
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Tempo de expiração*/
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        /*Exemplo de como ficara: Bearer djsjsj.dsdsds.jgjghg*/
        String token = TOKEN_PREFIX + " " + JWT;

        /*Da a resposta para tela e para o cliente, pode ser uma API, navegador, JS, outra chamada Java*/
        response.addHeader(HEADER_STRING, token);

        /*Usado para ver como fica no POSTMAN para teste*/
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }
}
