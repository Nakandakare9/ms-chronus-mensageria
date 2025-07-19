/*
package br.com.chronus.mensageria.infrastructure.service;

import br.com.chronus.mensageria.application.dto.EmailDTO;
import br.com.chronus.mensageria.exception.EmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = "email.sender=test@chronus.com")
class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Value("${email.sender}")
    private String remetente;

    private EmailDTO emailDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailDTO = EmailDTO.builder()
                .destinatario("destinatario@chronus.com")
                .assunto("Assunto de Teste")
                .mensagem("Mensagem de Teste")
                .build();
    }

    @Test
    void deveEnviarEmailComSucesso() {
        assertDoesNotThrow(() -> emailService.enviaEmail(emailDTO));
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailFalha() {
        doThrow(mock(MailException.class)).when(javaMailSender).send(any(SimpleMailMessage.class));

        EmailException ex = assertThrows(EmailException.class, () -> emailService.enviaEmail(emailDTO));
        assertTrue(ex.getMessage().contains("Falha ao enviar email"));
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
*/
