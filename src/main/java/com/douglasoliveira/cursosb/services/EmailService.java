package com.douglasoliveira.cursosb.services;

import org.springframework.mail.SimpleMailMessage;

import com.douglasoliveira.cursosb.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}

