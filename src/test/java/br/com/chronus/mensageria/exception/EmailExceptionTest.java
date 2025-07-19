package br.com.chronus.mensageria.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailExceptionTest {

    @Test
    void deveCriarEmailExceptionComMensagem() {
        EmailException exception = new EmailException("Erro ao enviar email");

        assertThat(exception.getMessage()).isEqualTo("Erro ao enviar email");
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void deveCriarEmailExceptionComMensagemECausa() {
        Throwable cause = new RuntimeException("Falha no SMTP");
        EmailException exception = new EmailException("Erro ao enviar email", cause);

        assertThat(exception.getMessage()).isEqualTo("Erro ao enviar email");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
