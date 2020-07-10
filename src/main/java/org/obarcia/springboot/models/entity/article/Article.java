package org.obarcia.springboot.models.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.components.Utilities;

/**
 * Artículo.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "article")
public class Article
{
    /**
     * Identificador.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * Tipo.
     */
    @NotEmpty
    @Size(max = 12)
    @Column(name = "type")
    private String type;
    /**
     * Título.
     */
    @NotEmpty
    @Size(max = 250)
    @Column(name = "title")
    private String title;
    /**
     * Descripción.
     */
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    /**
     * Imágen de portada.
     */
    @NotEmpty
    @Size(max = 250)
    @Column(name = "image")
    private String image;
    /**
     * Fecha de publicación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish")
    private Date publish;
    /**
     * Etiquetas.
     */
    @NotEmpty
    @Size(max = 128)
    @Column(name = "tags")
    private String tags;
    /**
     * Si es imporatante / destacado.
     */
    @Column(name = "important")
    private Boolean important;
    /**
     * Puntuación.
     */
    @Column(name = "score")
    private Double score;
    /**
     * Si está activo o no.
     */
    @Column(name = "active")
    private Boolean active;
    /**
     * Contenido.
     */
    @NotEmpty
    @Size(max = 9000)
    @JsonIgnore
    @Column(name = "content")
    private String content;
    /**
     * Listado de comentarios.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @JsonIgnore
    private final Set<Comment> comments = new HashSet<>();
    
    /**
     * Devuelve las etiquetas formateadas para visualización.
     * @return Etiquetas formateadas para visualización.
     */
    public String getFormattedTags()
    {
        if (tags != null) {
            return tags
                .replaceAll("\\]\\[", " ")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        }
        
        return "";
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
    
    public String getImage()
    {
        return image;
    }
    
    public void setImage(String value)
    {
        image = value;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String value)
    {
        type = value;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String value)
    {
        title = value;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String value)
    {
        description = value;
    }
    
    public Date getPublish()
    {
        return publish;
    }
    
    public void setPublish(Date value)
    {
        publish = value;
    }
    
    public String getTags()
    {
        return tags;
    }
    
    public void setTags(String value)
    {
        tags = value;
    }
    
    public Boolean getImportant()
    {
        return important;
    }
    
    public void setImportant(Boolean value)
    {
        important = value;
    }
    
    public Double getScore()
    {
        return score;
    }
    
    public void setScore(Double value)
    {
        score = value;
    }
    
    public Boolean getActive()
    {
        return active;
    }
    
    public void setActive(Boolean value)
    {
        active = value;
    }
    
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