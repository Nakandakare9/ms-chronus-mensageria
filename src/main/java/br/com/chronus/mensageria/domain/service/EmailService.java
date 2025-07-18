package br.com.chronus.mensageria.domain.service;

import br.com.chronus.mensageria.application.dto.EmailDTO;
import br.com.chronus.mensageria.exception.EmailException;

public interface EmailService {
    void enviaEmail(EmailDTO emailDTO) throws EmailException;
}