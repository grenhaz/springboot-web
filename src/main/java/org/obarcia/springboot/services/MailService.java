package org.obarcia.springboot.services;

import javax.servlet.http.HttpServletRequest;
import org.obarcia.springboot.models.entity.user.User;

/**
 * Servicio para el envío de emails.
 * 
 * @author obarcia
 */
public interface MailService
{
    /**
     * Enviar un email al usuario para activar su cuenta.
     * @param request Instancia de la petición.
     * @param user Instancia del usuario.
     */
    public void sendmailActivation(HttpServletRequest request, User user);
    /**
     * Enviar un email al usuario para recuperar su contraseña.
     * @param request Instancia de la petición.
     * @param user Instancia del usuario.
     */
    public void sendmailRecovery(HttpServletRequest request, User user);
}
