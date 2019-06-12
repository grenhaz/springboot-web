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
    private Integer offset = 0;
    /**
     * Número de registros por página.
     */
    private Integer limit = 0;
    /**
     * Total de registros.
     */
    private Long total = (long)0;
    /**
     * Registros.
     */
    private List records;
    
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
            return (total.intValue() / limit) + (total.intValue() % limit > 0 ? 1 : 0);
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
    public Long getTotal()
    {
        return total;
    }
    public void setTotal(Long value)
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
}