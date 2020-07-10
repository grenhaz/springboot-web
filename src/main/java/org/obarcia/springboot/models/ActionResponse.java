package org.obarcia.springboot.models;

/**
 * Respuesta ante una acción.
 * 
 * @author obarcia
 */
public class ActionResponse
{
    /**
     * Resultado de la operación.
     */
    private Boolean result = Boolean.FALSE;
    /**
     * Codigo del resultado.
     */
    private Integer code = 0;
    /**
     * Mensaje de error.
     */
    private String error = "";
    
    /**
     * Constructor de la clase.
     * @param r Resultado.
     * @param c Código.
     * @param e Error.
     */
    public ActionResponse(Boolean r, Integer c, String e)
    {
        result = r;
        code = c;
        error = e;
    }
    
    /**
     * Constructor de la clase.
     * @param r Resultado.
     */
    public ActionResponse(Boolean r)
    {
        result = r;
    }
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Boolean getResult()
    {
        return result;
    }
    
    public void setResult(Boolean value)
    {
        result = value;
    }
    
    public String getError()
    {
        return error;
    }
    
    public void setError(String value)
    {
        error = value;
    }
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer value)
    {
        code = value;
    }
}