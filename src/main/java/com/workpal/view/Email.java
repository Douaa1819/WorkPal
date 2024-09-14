package com.workpal.view;

import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public static String Mail(String email) {


        String to = email;


        String from = "douaachemnane@gmail.com";

        // Serveur SMTP utilisé pour l'envoi d'emails via Gmail
        String host = "smtp.gmail.com";

        // Configuration des propriétés du serveur
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Création d'une session avec authentification
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("douaachemnane@gmail.com", "ifjguijjtqcjmrba");
            }
        });

        // Désactivation du débogage SMTP
        // session.setDebug(true); -> Retiré pour ne pas afficher les logs de débogage

        String PasswordUUID = null;
        try {
            // Création du message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Réinitialisation de mot de passe");

            // Génération du mot de passe aléatoire
            PasswordUUID = UUID.randomUUID().toString();
            String emailContent = "Bonjour,\n\nVotre nouveau mot de passe est : " + PasswordUUID + "\n\nCordialement,\nVotre équipe.";

            // Ajout du contenu au message
            message.setText(emailContent);

            // Envoi du message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return PasswordUUID;
    }
}