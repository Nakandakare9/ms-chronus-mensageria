package br.com.chronus.mensageria.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MensagemDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveValidarMensagemDTOSucesso() {
        MensagemDTO dto = MensagemDTO.builder()
                .numero("11999999999")
                .mensagem("Olá, essa é uma mensagem de teste.")
                .build();

        Set<ConstraintViolation<MensagemDTO>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    void deveRetornarErroQuandoNumeroEstiverVazio() {
        MensagemDTO dto = MensagemDTO.builder()
                .numero("")
                .mensagem("Mensagem válida")
                .build();

        Set<ConstraintViolation<MensagemDTO>> violations = validator.validate(dto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Número é obrigatório");
    }

    @Test
    void deveRetornarErroQuandoMensagemEstiverVazia() {
        MensagemDTO dto = MensagemDTO.builder()
                .numero("11999999999")
                .mensagem("")
                .build();

        Set<ConstraintViolation<MensagemDTO>> violations = validator.validate(dto);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Mensagem é obrigatóris");
    }

    @Test
    void deveRetornarErrosQuandoTodosOsCamposEstaoInvalidos() {
        MensagemDTO dto = MensagemDTO.builder()
                .numero("")
                .mensagem("")
                .build();

        Set<ConstraintViolation<MensagemDTO>> violations = validator.validate(dto);

        assertThat(violations).hasSize(2);
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder("Número é obrigatório", "Mensagem é obrigatóris");
    }
}
