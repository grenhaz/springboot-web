package org.obarcia.springboot.models.forms.user;

import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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