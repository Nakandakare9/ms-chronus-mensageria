package br.com.chronus.mensageria.infrastructure.api;

import br.com.chronus.mensageria.application.dto.EmailDTO;
import br.com.chronus.mensageria.application.dto.MensagemDTO;
import br.com.chronus.mensageria.domain.service.EmailService;
import br.com.chronus.mensageria.domain.service.TelefoneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MensageriaController.class)
class MensageriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @MockBean
    private TelefoneService telefoneService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve enviar e-mail com sucesso")
    void deveEnviarEmailComSucesso() throws Exception {
        EmailDTO dto = EmailDTO.builder()
                .destinatario("teste@chronus.com")
                .assunto("Assunto")
                .mensagem("Conteúdo do email")
                .build();

        doNothing().when(emailService).enviaEmail(dto);

        mockMvc.perform(post("/chronus/mensageria/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Email enviado com sucesso"));
    }

    @Test
    @DisplayName("Deve enviar SMS com sucesso")
    void deveEnviarSMSComSucesso() throws Exception {
        MensagemDTO dto = MensagemDTO.builder()
                .numero("11999999999")
                .mensagem("Mensagem SMS")
                .build();

        doNothing().when(telefoneService).enviaSMS(dto.getNumero(), dto.getMensagem());

        mockMvc.perform(post("/chronus/mensageria/sms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

    @Test
    @DisplayName("Deve enviar WhatsApp com sucesso")
    void deveEnviarWhatsAppComSucesso() throws Exception {
        MensagemDTO dto = MensagemDTO.builder()
                .numero("11988888888")
                .mensagem("Mensagem WhatsApp")
                .build();

        doNothing().when(telefoneService).enviaWhatsApp(dto.getNumero(), dto.getMensagem());

        mockMvc.perform(post("/chronus/mensageria/whatsapp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios de EmailDTO")
    void deveValidarCamposObrigatoriosEmail() throws Exception {
        EmailDTO dto = new EmailDTO(); // vazio

        mockMvc.perform(post("/chronus/mensageria/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios de MensagemDTO")
    void deveValidarCamposObrigatoriosMensagem() throws Exception {
        MensagemDTO dto = new MensagemDTO(); // vazio

        mockMvc.perform(post("/chronus/mensageria/sms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
