package org.obarcia.springboot.services;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.obarcia.springboot.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de envío de emails.
 * 
 * @author obarcia
 */
@Service
public class MailServiceImpl implements MailService
{
    /**
     * Instancia del servicio para el envío de emails.
     */
    @Autowired
    private JavaSendMail mailSender;
    
    @Override
    public void sendmailActivation(HttpServletRequest request, User user)
    {
        String url = request.getContextPath() + "/user/activate?k=" + user.getUkey();
        
        try {
            String htmlMsg = "" +
                    "<p>Pulse sobre el <a href='" + url + "'>ENLACE</a> para la activación de la cuenta.";
            
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Activación de la cuenta");
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void sendmailRecovery(HttpServletRequest request, User user)
    {
        String url = request.getContextPath() + "/user/recover?k=" + user.getUkey();
        
        try {
            String htmlMsg = "" +
                    "<p>Pulse sobre el <a href='" + url + "'>ENLACE</a> para la recuperación de la cuenta.";
            
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Recuperación de la cuenta");
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}