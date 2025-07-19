package br.com.chronus.mensageria.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EmailDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidatorInstance() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveValidarEmailDTOValido() {
        EmailDTO dto = EmailDTO.builder()
                .destinatario("usuario@email.com")
                .assunto("Assunto de Teste")
                .mensagem("Conteúdo da mensagem")
                .build();

        Set<ConstraintViolation<EmailDTO>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    void deveFalharQuandoDestinatarioInvalido() {
        EmailDTO dto = EmailDTO.builder()
                .destinatario("email-invalido") // formato inválido
                .assunto("Assunto")
                .mensagem("Mensagem")
                .build();

        Set<ConstraintViolation<EmailDTO>> violations = validator.validate(dto);

        assertThat(violations).extracting(ConstraintViolation::getMessage)
                .contains("Email inválido");
    }

    @Test
    void deveFalharQuandoCamposObrigatoriosEstaoVazios() {
        EmailDTO dto = new EmailDTO();

        Set<ConstraintViolation<EmailDTO>> violations = validator.validate(dto);

        assertThat(violations).extracting(ConstraintViolation::getMessage)
                .contains("Destinatário é obrigatório",
                        "Assunto é obrigatório",
                        "Mensagem é obrigatória");
    }
}
