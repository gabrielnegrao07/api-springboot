package br.com.curso.apispringboot.apispringboot.service;

import br.com.curso.apispringboot.apispringboot.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);
}
