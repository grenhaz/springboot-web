package org.obarcia.springboot.models.user;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Formario para recurer la contraseña del usuario.
 * 
 * @author obarcia
 */
public class ForgotForm
{
    /**
     * Email
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Email(message = "{error.Email}")
    @Size(max = 128)
    private String email;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
}