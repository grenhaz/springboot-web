package org.obarcia.springboot.models.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.obarcia.springboot.models.user.UserLite;

/**
 * Comentario de un artículo (Lite).
 * 
 * @author obarcia
 */
@Entity
@Immutable
@Table(name = "comment")
public class CommentLite extends CommentBase
{
    /**
     * Artículo.
     */
    @Column(name = "id_article")
    private Integer id_article;
    /**
     * Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private UserLite user;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Integer getIdArticle()
    {
        return id_article;
    }
    public void setIdArticle(Integer value)
    {
        id_article = value;
    }
    public UserLite getUser()
    {
        return user;
    }
    public void setUser(UserLite value)
    {
        user = value;
    }
}