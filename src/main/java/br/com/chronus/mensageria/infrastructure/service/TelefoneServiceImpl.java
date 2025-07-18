package br.com.chronus.mensageria.infrastructure.service;

import br.com.chronus.mensageria.domain.service.TelefoneService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelefoneServiceImpl implements TelefoneService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @Value("${twilio.whatsapp-number}")
    private String twilioWhatsappNumber;

    @Override
    public void enviaSMS(String to, String mensagem) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioPhoneNumber),
                mensagem
        ).create();
    }

    @Override
    public void enviaWhatsApp(String to, String mensagem) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(twilioWhatsappNumber),
                mensagem
        ).create();
    }
}
