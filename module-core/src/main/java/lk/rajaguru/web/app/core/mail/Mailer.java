package lk.rajaguru.web.app.core.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lk.rajaguru.web.app.core.provider.MailServiceProvider;

public abstract class Mailer implements Runnable{
    private MailServiceProvider provider;

    public Mailer() {
        provider = MailServiceProvider.getInstance();
    }
    @Override
    public void run() {
        try {
            Session session = provider.getSession();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@rajaguru.com"));
            build(message);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void build(MimeMessage message) throws MessagingException;
}
