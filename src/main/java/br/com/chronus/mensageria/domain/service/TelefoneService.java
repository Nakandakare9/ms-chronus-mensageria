package br.com.chronus.mensageria.domain.service;

public interface TelefoneService {
    void enviaSMS(String to, String mensagem);
    void enviaWhatsApp(String to, String mensagem);
}