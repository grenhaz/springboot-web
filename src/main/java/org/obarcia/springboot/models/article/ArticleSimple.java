package org.obarcia.springboot.models.article;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Artículo.
 * 
 * @author obarcia
 */
@Entity
@Immutable
@Table(name = "article")
public class ArticleSimple extends ArticleBase
{
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
    public Set<Comment> getComments()
    {
        return comments;
    }
    public int getCommentsCount()
    {
        return comments.size();
    }
}