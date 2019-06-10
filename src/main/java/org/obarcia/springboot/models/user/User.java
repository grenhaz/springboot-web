package org.obarcia.springboot.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "usuario")
public class User extends UserBase
{
    /**
     * Contraseña.
     */
    @NotEmpty
    @Size(max = 64)
    @Column(name = "password")
    private String password;
    /**
     * Clave para activación y cambio de contraseña.
     */
    @Size(max = 64)
    @Column(name = "ukey")
    private String ukey;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
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