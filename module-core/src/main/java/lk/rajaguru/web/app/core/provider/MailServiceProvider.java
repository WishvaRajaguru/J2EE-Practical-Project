package lk.rajaguru.web.app.core.provider;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lk.rajaguru.web.app.core.mail.Mailer;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MailServiceProvider {

    private static MailServiceProvider instance;
    private Authenticator authenticator;
    private Properties properties;
    // The tread pool that will manage email sending
    private ThreadPoolExecutor executor;
    // The queue where the mail sending tasks will be queued
    private BlockingQueue<Runnable> blockingQueue;

    private MailServiceProvider() {
        // instantiate properties and add them
        properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");
        // instantiate authenticator with credentials form mailtrap
        authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("0b6fa81fe456ef","7ea49b0f46d1a6");
            }
        };
        blockingQueue = new LinkedBlockingQueue<Runnable>();
    }

    public static MailServiceProvider getInstance() {
        if (MailServiceProvider.instance == null) {
            instance = new MailServiceProvider();
        }
        return instance;
    }

    public void start(){
        if (executor == null) {
            executor = new ThreadPoolExecutor(5, 8, 5, TimeUnit.SECONDS, blockingQueue, new ThreadPoolExecutor.AbortPolicy());
            executor.prestartAllCoreThreads();
            System.out.println("Starting Mail Service Provider....");
        }else{
            System.out.println("Mail Service Provider already started....");
        }
    }

    public Session getSession() {
        return Session.getInstance(properties, authenticator);
    }
    
    public void queueMailer(Mailer mailer){
        if(!blockingQueue.offer(mailer)){
            System.out.println("Mailer queue is full....");
        }
    }

    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
