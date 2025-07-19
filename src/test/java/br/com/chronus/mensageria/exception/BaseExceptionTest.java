package br.com.chronus.mensageria.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseExceptionTest {

    static class TesteBaseException extends BaseException {
        public TesteBaseException(String message) {
            super(message);
        }

        public TesteBaseException(Throwable throwable) {
            super(throwable);
        }

        public TesteBaseException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }

    @Test
    void deveCriarExcecaoComMensagem() {
        TesteBaseException exception = new TesteBaseException("Erro simulado");

        assertThat(exception.getMessage()).isEqualTo("Erro simulado");
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void deveCriarExcecaoComCausa() {
        Throwable cause = new RuntimeException("Causa interna");
        TesteBaseException exception = new TesteBaseException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
        assertThat(exception.getMessage()).isEqualTo("java.lang.RuntimeException: Causa interna");
    }

    @Test
    void deveCriarExcecaoComMensagemECausa() {
        Throwable cause = new IllegalArgumentException("Parametro inválido");
        TesteBaseException exception = new TesteBaseException("Erro ao processar", cause);

        assertThat(exception.getMessage()).isEqualTo("Erro ao processar");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
