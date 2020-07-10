package org.obarcia.springboot.models.entity.article;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.obarcia.springboot.components.Utilities;
import org.obarcia.springboot.models.entity.user.User;

/**
 * Comentario de un artículo.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "comment")
public class Comment
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
     * Artículo.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_article", nullable = false)
    private Article article;
    /**
     * Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
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
    
    public Article getArticle()
    {
        return article;
    }
    
    public void setArticle(Article value)
    {
        article = value;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User value)
    {
        user = value;
    }
}