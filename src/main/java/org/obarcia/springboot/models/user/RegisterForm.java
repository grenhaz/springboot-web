package org.obarcia.springboot.models.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.constraints.EmailUniqueConstraint;
import org.obarcia.springboot.constraints.NicknameConstraint;
import org.obarcia.springboot.constraints.FieldEqualConstraint;

/**
 * Formulario de registro.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
@EmailUniqueConstraint(field = "email", message = "{error.email.unique}")
@FieldEqualConstraint(first = "email", second = "emailRepeat", message = "{error.email.repeat}")
public class RegisterForm
{
    /**
     * Email.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Email(message = "{error.Email}")
    @Size(max = 128)
    private String email;
    /**
     * Email (Confirmación).
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Email(message = "{error.Email}")
    @Size(max = 128)
    private String emailRepeat;
    /**
     * Nickname.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "{error.nickname.format}")
    private String nickname;
    
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
    public String getEmailRepeat()
    {
        return emailRepeat;
    }
    public void setEmailRepeat(String value)
    {
        emailRepeat = value;
    }
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String value)
    {
        nickname = value;
    }
}