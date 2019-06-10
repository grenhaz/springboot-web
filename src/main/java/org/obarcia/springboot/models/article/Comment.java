package org.obarcia.springboot.models.article;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.obarcia.springboot.models.user.User;

/**
 * Comentario de un artículo.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "comment")
public class Comment extends CommentBase
{
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
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
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