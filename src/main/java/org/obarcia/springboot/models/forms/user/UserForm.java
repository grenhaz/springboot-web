package org.obarcia.springboot.models.forms.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.constraints.EmailUniqueConstraint;
import org.obarcia.springboot.constraints.NicknameConstraint;

/**
 * Formulario del usuario.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
@EmailUniqueConstraint(field = "email", message = "{error.email.unique}")
public class UserForm
{
    /**
     * Identificador.
     */
    private Integer id;
    /**
     * Email.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Email
    @Size(max = 128)
    private String email;
    /**
     * Nickname.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.NicknamePattern}")
    private String nickname;
    
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
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
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