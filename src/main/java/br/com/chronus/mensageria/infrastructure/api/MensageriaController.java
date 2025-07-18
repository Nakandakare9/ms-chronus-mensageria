package br.com.chronus.mensageria.infrastructure.api;

import br.com.chronus.mensageria.application.dto.EmailDTO;
import br.com.chronus.mensageria.application.dto.MensagemDTO;
import br.com.chronus.mensageria.domain.service.EmailService;
import br.com.chronus.mensageria.domain.service.TelefoneService;
import br.com.chronus.mensageria.exception.BaseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chronus/mensageria")
public class MensageriaController {

    @Autowired
    private EmailService notificationService;

    @Autowired
    private TelefoneService telefoneService;

    @PostMapping("/email")
    public ResponseEntity<Object> sendSimpleEmail(@Valid @RequestBody EmailDTO emailDTO) throws BaseException {
        notificationService.enviaEmail(emailDTO);
        return new ResponseEntity<>("Email enviado com sucesso", HttpStatus.OK);
    }

    @PostMapping("/sms")
    public ResponseEntity<Object> sendsimpleSMS(@Valid @RequestBody MensagemDTO mensagem) throws BaseException {
        telefoneService.enviaSMS(mensagem.getNumero(), mensagem.getMensagem());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/whatsapp")
    public ResponseEntity<Object> sendWhats(@Valid @RequestBody MensagemDTO mensagem) throws BaseException {
        telefoneService.enviaWhatsApp(mensagem.getNumero(), mensagem.getMensagem());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}