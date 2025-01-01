package com.meuprojeto.service;

import com.meuprojeto.model.Usuario;
import com.meuprojeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
@Service
public class TarefaAutomatizadaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServiceSendEmail serviceSendEmail;

    //@Scheduled(initialDelay = 2000, fixedDelay = 86400000) //Roda a cada 24 horas
    //@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") //Vai rodar todo dia as 11 horas da manha horario de sao paulo
    public void notificarUserTrocarSenha() throws MessagingException, UnsupportedEncodingException, InterruptedException {

        List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();

        for(Usuario usuario : usuarios){

            StringBuilder msg = new StringBuilder();
            msg.append("Ola, ") .append(usuario.getPessoa().getNome()).append("<br/>");
            msg.append("Está na hora de trocar sua senha, já passou 90 dias").append("<br/>");
            msg.append("Troque sua senha da loja virtual do Raphael");

            serviceSendEmail.enviarEmailHtml("Troca de Senha", msg.toString(), usuario.getLogin());

            Thread.sleep(4000);

        }

    }
}
