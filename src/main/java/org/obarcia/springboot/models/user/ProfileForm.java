package org.obarcia.springboot.models.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.constraints.NicknameConstraint;

/**
 * Formulario de perfil del usuario.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
public class ProfileForm
{
    /**
     * Identificador del usuario.
     */
    private Integer id;
    /**
     * Nickname.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.NicknamePattern}")
    private String nickname;
    /**
     * Fichero que hace de avatar.
     */
    @Size(max = 64)
    private String avatar;
    
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
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String value)
    {
        nickname = value;
    }
    public String getAvatar()
    {
        return avatar;
    }
    public void setAvatar(String value)
    {
        avatar = value;
    }
}