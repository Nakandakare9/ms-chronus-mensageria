package br.com.chronus.mensageria.infrastructure.service;

import br.com.chronus.mensageria.application.dto.EmailDTO;
import br.com.chronus.mensageria.domain.service.EmailService;
import br.com.chronus.mensageria.exception.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${email.sender}")
    private String remetente;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void enviaEmail(EmailDTO emailDTO) throws EmailException {
        LOG.info("Enviando email para: {}", emailDTO.getDestinatario());

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(remetente);
        mensagem.setTo(emailDTO.getDestinatario());
        mensagem.setSubject(emailDTO.getAssunto());
        mensagem.setText(emailDTO.getMensagem());

        try {
            javaMailSender.send(mensagem);
            LOG.info("Email enviado com sucesso");
        } catch (MailException ex) {
            LOG.error("Erro ao enviar email: {}", ex.getMessage());
            throw new EmailException("Falha ao enviar email: " + ex.getMessage());
        }
    }
}