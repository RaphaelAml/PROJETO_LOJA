package com.meuprojeto.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/*Filtro onde todas as requisicoes serão capturadas para autenticar*/
public class JwtApiAutenticacaoFilter extends GenericFilterBean {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        /*Estabele a autenticação do user*/
        Authentication authentication = new JWTTokenAutenticacaoService()
                .getAuthetication((HttpServletRequest) request, (HttpServletResponse) response);

        /*Coloca o processo de autenticacao para o Spring Security*/
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest,
                         jakarta.servlet.ServletResponse servletResponse,
                         jakarta.servlet.FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {

    }
}
