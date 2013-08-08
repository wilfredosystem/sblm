package com.sblm.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {

	public void enviarCorreo(String mailDestino,String asunto, String mensaje) {
		
		try {
			Properties props = new Properties();

			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "wilfredohuaira@gmail.com");
			props.setProperty("mail.smtp.auth", "false");

			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(
					"wilfredohuaira@gmail.com"));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(mailDestino));

			message.setSubject(asunto);

			message.setText(mensaje);

			Transport t = session.getTransport("smtp");

			t.connect("smtp.gmail.com", "wilfredohuaira@gmail.com",
					"honestidad");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (Exception e) {
			System.out.println("error correo:::"+e.getMessage());
		}
		
	}
}
