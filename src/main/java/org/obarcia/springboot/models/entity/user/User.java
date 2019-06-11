package org.obarcia.springboot.models.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.obarcia.springboot.components.Utilities;

/**
 * User.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "usuario")
public class User
{
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    
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
     * Contraseña.
     */
    @NotEmpty
    @Size(max = 64)
    @JsonIgnore
    @Column(name = "password")
    private String password;
    /**
     * Clave para activación y cambio de contraseña.
     */
    @Size(max = 64)
    @JsonIgnore
    @Column(name = "ukey")
    private String ukey;
    
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
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String value)
    {
        password = value;
    }
    public String getUkey()
    {
        return ukey;
    }
    public void setUkey(String value)
    {
        ukey = value;
    }
}