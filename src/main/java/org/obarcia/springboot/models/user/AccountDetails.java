package org.obarcia.springboot.models.user;

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
        super(username, password, authorities);
        
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
    }
    /**
     * Constructor de la clase.
     * @param username Nombre del usuario.
     * @param password Contraseña.
     * @param enabled Habilitado.
     * @param accountNonExpired Si la cuenta expira.
     * @param credentialsNonExpired Si las credenciles expiran.
     * @param accountNonLocked Si la cuenta no está bloqueada.
     * @param authorities Autorizaciones (Roles).
     * @param nickname Nickname.
     * @param avatar Fichero del avatar.
     * @param id Identificador del usuario.
     */
    public AccountDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String nickname, String avatar, int id)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        
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
        return (avatar != null && !avatar.isEmpty() ? avatar : "anonymous.png");
    }
    public void setAvatar(String value)
    {
        avatar = value;
    }
}