package com.meuprojeto.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meuprojeto.model.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /*Configurando o gerenciador de autenticação*/
    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        /*Obriga a autenticação na URL*/
        super(new AntPathRequestMatcher(url));

        /*Gerenciador de autenticação*/
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(jakarta.servlet.http.HttpServletRequest request,
                                                jakarta.servlet.http.HttpServletResponse response)
            throws AuthenticationException, IOException, jakarta.servlet.ServletException {
        return null;
    }


    /*Retorna o usuário ao processr a autenticacao*/
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        /*Obter o usuário*/
        Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

        /*Retorna o user com login e senha*/
        return getAuthenticationManager().
                authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
    }



    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        try {
            new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

