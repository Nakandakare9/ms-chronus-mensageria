# 📡 ms-chronus-mensageria

Este microsserviço integra a arquitetura do projeto **CHRONUS** e é responsável pela **comunicação assíncrona** e envio de **notificações automatizadas** para pacientes e profissionais de saúde por meio de **mensagens SMS, WhatsApp e e-mail**.

## 📌 Objetivo

Automatizar o envio de mensagens e notificações no ecossistema CHRONUS, reforçando a **adesão ao tratamento**, **confirmação de consultas** e **comunicação entre pacientes e UBSs**, através de canais acessíveis e integrados com os demais microsserviços.

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **RabbitMQ** (mensageria assíncrona)
- **Spring Mail** (envio de e-mails via SMTP)
- **Twilio API** (integração com SMS e WhatsApp)
- **Docker** (execução em contêineres)
- **H2/PostgreSQL** (desenvolvimento/produção)

## 🧩 Funcionalidades

- 📬 Envio de notificações por **e-mail (via Gmail SMTP)**
- 📱 Envio de mensagens **SMS e WhatsApp** com **Twilio**
- 🔄 Escuta de eventos por **RabbitMQ**
- 💬 Simulação de mensagens para testes
- ✅ Preparação para integração com microsserviços `ms-chronus-pessoas` e `ms-chronus-gerenciamento`

## 📤 Integração com Twilio: SMS e WhatsApp

O serviço `TelefoneServiceImpl` implementa o envio de notificações por:

- **SMS direto**
- **WhatsApp Business API** (via número Twilio autorizado)

### Exemplo de método:

```java
public void enviaSMS() {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message.creator(
        new PhoneNumber("+55XXXXXXXXXXX"), 
        new PhoneNumber("+1XXXXXXXXXX"), 
        "Tome seus remédios"
    ).create();
}
```

```java
public void enviaWhatsApp(String to, String body) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message.creator(
        new PhoneNumber("whatsapp:+55XXXXXXXXXXX"),
        new PhoneNumber("whatsapp:+14155238886"),
        body
    ).create();
}
```

> ⚠️ **Importante:** O uso da Twilio requer cadastro e verificação de número para envio. As credenciais devem ser **armazenadas com segurança (ex: variáveis de ambiente)**.  
> As chaves estão atualmente **hardcoded** e **devem ser removidas** do código-fonte antes de qualquer deploy.

## 📧 Integração com SMTP (E-mail)

A aplicação permite o envio de e-mails via **Gmail SMTP** com as seguintes configurações:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.username=chronusfiap@gmail.com
spring.mail.password=********
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```


## 🐇 Integração com RabbitMQ

O serviço escuta eventos publicados por outros microsserviços para acionar o envio de mensagens automaticamente.

Exemplo de eventos que podem acionar o serviço:
- Novo agendamento confirmado
- Falta detectada
- Prescrição prestes a vencer
- Notificação de acompanhamento


## 🗂️ Organização de Pacotes

```
br.com.chronus.mensageria
├── domain.service          # Interfaces de serviço
├── infrastructure.service  # Implementações (Twilio, Mail)
├── infrastructure.config   # Configurações gerais (Rabbit, SMTP)
├── messaging.consumer      # Listeners de eventos
└── application             # Camada de lógica de aplicação
```

## 🔗 Links Úteis

- [Twilio API Docs](https://www.twilio.com/docs)
- [Spring Boot Mail Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-email)
- [RabbitMQ Official](https://www.rabbitmq.com/)

## 🤝 Contribuidores

- [@Nakandakare9](https://github.com/Nakandakare9)
- [@CamilaLima21](https://github.com/CamilaLima21)
