package org.obarcia.springboot.models.article;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.validation.constraints.NotEmpty;

/**
 * Artículo.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "article")
public class Article extends ArticleBase
{
    /**
     * Contenido.
     */
    @NotEmpty
    @Size(max = 9000)
    @Column(name = "content")
    private String content;
    /**
     * Listado de comentarios.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private final Set<Comment> comments = new HashSet<>();
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
    public Set<Comment> getComments()
    {
        return comments;
    }
    public int getCommentsCount()
    {
        return comments.size();
    }
}