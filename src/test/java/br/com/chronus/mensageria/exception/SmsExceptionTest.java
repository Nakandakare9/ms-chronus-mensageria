package br.com.chronus.mensageria.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SmsExceptionTest {

    @Test
    void deveCriarSmsExceptionComMensagem() {
        SmsException exception = new SmsException("Erro ao enviar SMS");

        assertThat(exception.getMessage()).isEqualTo("Erro ao enviar SMS");
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void deveCriarSmsExceptionComMensagemECausa() {
        Throwable cause = new RuntimeException("Falha na API de SMS");
        SmsException exception = new SmsException("Erro ao enviar SMS", cause);

        assertThat(exception.getMessage()).isEqualTo("Erro ao enviar SMS");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
