package org.obarcia.springboot.models.article;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.obarcia.springboot.components.Utilities;

/**
 * Superclase para los comentarios.
 * 
 * @author obarcia
 */
@MappedSuperclass
public class CommentBase implements Serializable
{
    /**
     * Identificador.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * Contenido.
     */
    @NotEmpty
    @Size(max = 512)
    @Column(name = "content")
    private String content;
    /**
     * Fecha de publicación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish")
    private Date publish;
    /**
     * Si el comentario ha sido borrado
     */
    @Column(name = "erased")
    private Boolean erased = Boolean.FALSE;
    
    /**
     * Devuelve el contenido reducido.
     * @return Contenido reducido.
     */
    public String getShortContent()
    {
        if (content.length() > 83) {
            return content.substring(0, 80) + "...";
        }
        
        return content;
    }
    /**
     * Devuelve la fecha de publicación formateada.
     * @return Fecha de publicación formateada.
     */
    public String getFormattedPublish()
    {
        return Utilities.getElapsedTime(publish);
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
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
    public Date getPublish()
    {
        return publish;
    }
    public void setPublish(Date value)
    {
        publish = value;
    }
    public Boolean getErased()
    {
        return erased;
    }
    public void setErased(Boolean value)
    {
        erased = value;
    }
}