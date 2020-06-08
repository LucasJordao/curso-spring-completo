package com.lucaswilliam.cursospringcompleto.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucaswilliam.cursospringcompleto.domains.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);
}
