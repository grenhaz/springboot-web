package org.obarcia.springboot.models;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Implementación del UserDetails para usuario validado en el sistema.
 * 
 * @author obarcia
 */
public class AccountDetails extends User
{
    /**
     * Identificador del usuario.
     */
    private Integer id;
    /**
     * Nickname.
     */
    private String nickname;
    /**
     * Fichero que hace de avatar.
     */
    private String avatar;
    
    /**
     * Constructor de la clase.
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @param authorities Autorizaciones (Roles).
     * @param nickname Nickname.
     * @param avatar Fichero del avatar.
     * @param id Identificador del usuario.
     */
    public AccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String nickname, String avatar, int id)
    {
        super(username, password, true, true, true, true, authorities);
        
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
    }
    
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
        if (avatar != null && !avatar.isEmpty()) {
            return avatar;
        } else {
            return "anonymous.png";
        }
    }
    
    public void setAvatar(String value)
    {
        avatar = value;
    }
}