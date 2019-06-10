package org.obarcia.springboot.models;

import java.util.List;

/**
 * Clase para el manejo de listados paginados.
 * 
 * @author obarcia
 */
public class ListPagination<T>
{
    /**
     * Inicio.
     */
    private Integer offset;
    /**
     * Número de registros por página.
     */
    private Integer limit;
    /**
     * Total de registros.
     */
    private Integer total;
    /**
     * Registros.
     */
    private List records;
    /**
     * Tipo de registros.
     */
    private String type;
    /**
     * Etiqueta de los registros.
     */
    private String tag;
    
    /**
     * Devuelve la página actual.
     * @return Página actual.
     */
    public Integer getCurrent()
    {
        if (limit > 0) {
            return (offset / limit) + 1;
        }
        
        return 1;
    }
    /**
     * Devuelve el número de páginas.
     * @return Número de paginas.
     */
    public Integer getPages()
    {
        if (limit > 0) {
            return (total / limit) + (total % limit > 0 ? 1 : 0);
        }
        
        return 1;
    }
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Integer getOffset()
    {
        return offset;
    }
    public void setOffset(Integer value)
    {
        offset = value;
    }
    public Integer getLimit()
    {
        return limit;
    }
    public void setLimit(Integer value)
    {
        limit = value;
    }
    public Integer getTotal()
    {
        return total;
    }
    public void setTotal(Integer value)
    {
        total = value;
    }
    public List<T> getRecords()
    {
        return records;
    }
    public void setRecords(List<T> value)
    {
        records = value;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String value)
    {
        type = value;
    }
    public String getTag()
    {
        return tag;
    }
    public void setTag(String value)
    {
        tag = value;
    }
}