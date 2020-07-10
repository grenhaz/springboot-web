package org.obarcia.springboot.models.forms.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.constraints.FieldEqualConstraint;

/**
 * Formulario para cambiar la contraseña de un usuario.
 * 
 * @author obarcia
 */
@FieldEqualConstraint(first = "password", second = "passwordRepeat", message = "{error.password.repeat}")
public class PasswordForm
{
    /**
     * Identificador del usuario.
     */
    private Integer id;
    /**
     * Contraseña.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.PasswordPattern}")
    private String password;
    /**
     * Contraseña (Confirmación).
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.PasswordPattern}")
    private String passwordRepeat;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer value)
    {
        id = value;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String value)
    {
        password = value;
    }
    
    public String getPasswordRepeat()
    {
        return passwordRepeat;
    }
    
    public void setPasswordRepeat(String value)
    {
        passwordRepeat = value;
    }
}