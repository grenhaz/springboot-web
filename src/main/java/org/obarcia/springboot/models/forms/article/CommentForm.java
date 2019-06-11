package org.obarcia.springboot.models.forms.article;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

/**
 * Formulario de nuevo comentario.
 * 
 * @author obarcia
 */
public class CommentForm
{
    /**
     * Contenido del formulario.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 512)
    private String content;
    
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
}
