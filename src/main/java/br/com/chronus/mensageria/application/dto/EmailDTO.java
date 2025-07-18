package br.com.chronus.mensageria.application.dto;

import jakarta.validation.constraints.Email;
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
public class EmailDTO {

    @Email(message = "Email inválido")
    @NotBlank(message = "Destinatário é obrigatório")
    private String destinatario;

    @NotBlank(message = "Assunto é obrigatório")
    private String assunto;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;
}
