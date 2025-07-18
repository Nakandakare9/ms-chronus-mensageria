package br.com.chronus.mensageria.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensagemDTO {

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Mensagem é obrigatóris")
    private String mensagem;
}