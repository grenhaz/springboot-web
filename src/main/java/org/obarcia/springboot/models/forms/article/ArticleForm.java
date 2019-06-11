package org.obarcia.springboot.models.forms.article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import org.obarcia.springboot.constraints.ArrayNotEmptyConstraint;

/**
 * Formulario del artículo.
 * 
 * @author obarcia
 */
@ArrayNotEmptyConstraint(message = "{error.ArrayNotEmptyConstraint}", field = "tags")
public class ArticleForm
{
    /**
     * Formato de fecha
     */
    private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat FORMAT_FORM = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    /**
     * Identificador.
     */
    @GeneratedValue
    private Integer id;
    /**
     * Tipo.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 12)
    private String type;
    /**
     * Título.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 250)
    private String title;
    /**
     * Descripción.
     */
    @Size(max = 250)
    private String description;
    /**
     * Imágen de portada.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 250)
    private String image;
    /**
     * Contenido.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 9000)
    private String content;
    /**
     * Fecha de publicación.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    private String publishDate;
    /**
     * Hora de publicación.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    private String publishTime;
    /**
     * Etiquetas.
     */
    private final List<String> tags = new ArrayList<>();
    /**
     * Si es imporatante / destacado.
     */
    private Boolean important;
    /**
     * Puntuación.
     */
    private Double score;
    /**
     * Si está activo o no.
     */
    private Boolean active;
    
    /**
     * Devuelve las etiquetas.
     * @return Valor de las etiquetas.
     */
    public String getTags()
    {
        if (tags.size() > 0) {
            return "[" + String.join("][", tags) + "]";
        }
        
        return "";
    }
    /**
     * Asigna las etiquetas.
     * @param value Valor de las etiquetas.
     */
    public void setTags(String value)
    {
        tags.clear();
        if (value != null) {
            String[] list = value
                .replaceAll("\\]\\[", ",")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .split(",");
            if (list != null && list.length > 0) {
                for (String l: list) {
                    tags.add(l);
                }
            }
        }
    }
    /**
     * Devuelve la fecha de publicación.
     * 
     * @return Valor de la fecha.
     */
    public Date getPublish()
    {
        try {
            return FORMAT_FORM.parse(publishDate + " " + publishTime);
        } catch (ParseException ex) {}
        
        return null;
    }
    /**
     * Asigna la fecha de publicacción.
     * @param value Valor de la fecha.
     */
    public void setPublish(Date value)
    {
        if (value == null) {
            value = new Date();
        }
        publishDate = FORMAT_DATE.format(value);
        publishTime = FORMAT_TIME.format(value);
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
    public String getImage()
    {
        return image;
    }
    public void setImage(String value)
    {
        image = value;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
    public String getPublishDate()
    {
        return publishDate;
    }
    public void setPublishDate(String value)
    {
        publishDate = value;
    }
    public String getPublishTime()
    {
        return publishTime;
    }
    public void setPublishTime(String value)
    {
        publishTime = value;
    }
    public List<String> getTagsCheckbox()
    {
        return tags;
    }
    public void setTagsCheckbox(List<String> value)
    {
        tags.clear();
        tags.addAll(value);
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
}