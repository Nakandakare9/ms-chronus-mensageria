/*
package br.com.chronus.mensageria.infrastructure.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

class TelefoneServiceImplTest {

    private TelefoneServiceImpl telefoneService;

    @BeforeEach
    void setUp() {
        telefoneService = new TelefoneServiceImpl();
        // definir os campos com Reflection se necessário
        ReflectionTestUtils.setField(telefoneService, "accountSid", "dummy");
        ReflectionTestUtils.setField(telefoneService, "authToken", "dummy");
        ReflectionTestUtils.setField(telefoneService, "twilioPhoneNumber", "+123456789");
        ReflectionTestUtils.setField(telefoneService, "twilioWhatsappNumber", "+987654321");
    }

    @Test
    void deveEnviarSMSComSucesso() {
        try (MockedStatic<Message> mockedMessage = mockStatic(Message.class)) {
            // Mock do Creator, que tem o método create()
            Message.Creator creatorMock = mock(Message.Creator.class);
            when(creatorMock.create()).thenReturn(null);

            // Quando Message.creator(...) for chamado, retorna nosso mock do Creator
            mockedMessage.when(() -> Message.creator(
                            any(PhoneNumber.class),
                            any(PhoneNumber.class),
                            anyString()))
                    .thenReturn(creatorMock);

            // Executa método que usa Message.creator().create()
            telefoneService.enviaSMS("+550000000000", "Teste SMS");

            // Verifica que o create foi chamado
            verify(creatorMock).create();
        }
    }

    @Test
    void deveEnviarWhatsAppComSucesso() {
        try (MockedStatic<Message> mockedMessage = mockStatic(Message.class)) {
            Message.Creator creatorMock = mock(Message.Creator.class);
            when(creatorMock.create()).thenReturn(null);

            mockedMessage.when(() -> Message.creator(
                            any(PhoneNumber.class),
                            any(PhoneNumber.class),
                            anyString()))
                    .thenReturn(creatorMock);

            telefoneService.enviaWhatsApp("+550000000000", "Teste WhatsApp");

            verify(creatorMock).create();
        }
    }
}
*/
