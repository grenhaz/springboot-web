package org.obarcia.springboot.models.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.components.Utilities;

/**
 * User.
 * 
 * @author obarcia
 */
@MappedSuperclass
public class UserBase implements Serializable
{
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    
    /**
     * Identificador.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * Email.
     */
    @NotEmpty
    @Email
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    /**
     * Nickname.
     */
    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @Column(name = "nickname")
    private String nickname;
    /**
     * Rol
     */
    @NotEmpty
    @Size(max = 16)
    @Column(name = "user_role")
    private String user_role;
    /**
     * Usuario activado o no.
     */
    @Column(name = "active")
    private Boolean active;
    /**
     * Fichero que hace de avatar.
     */
    @Size(max = 64)
    @Column(name = "avatar")
    private String avatar;
    /**
     * Fecha de creación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;
    
    /**
     * Devuelve la fecha de alta formateada.
     * @return Fecha de alta formateada.
     */
    public String getFormattedCreated()
    {
        return Utilities.getElapsedTime(created);
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
    public String getUserRole()
    {
        return user_role;
    }
    public void setUserRole(String value)
    {
        user_role = value;
    }
    public Boolean getActive()
    {
        return active;
    }
    public void setActive(Boolean value)
    {
        active = value;
    }
    public String getAvatar()
    {
        return avatar;
    }
    public void setAvatar(String value)
    {
        avatar = value;
    }
    public Date getCreated()
    {
        return created;
    }
    public void setCreated(Date value)
    {
        created = value;
    }
}