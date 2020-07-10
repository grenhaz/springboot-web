package org.obarcia.springboot.components.datatables;

import java.util.List;

/**
 * DataTables: Respuesta
 * 
 * @author obarcia
 */
public class DataTablesResponse<T>
{
    /**
     * draw: Debecoincidir con el draw de la petición
     */
    private Integer draw = 1;
    /**
     * Total de registros.
     */
    private Long recordsTotal = (long) 0;
    /**
     * Total de registros filtrados.
     */
    private Long recordsFiltered = (long) 0;
    /**
     * Registros.
     */
    private List<T> data;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Integer getDraw()
    {
        return draw;
    }
    
    public void setDraw(Integer value)
    {
        draw = value;
    }
    
    public Long getRecordsTotal()
    {
        return recordsTotal;
    }
    
    public void setRecordsTotal(Long value)
    {
        recordsTotal = value;
    }
    
    public Long getRecordsFiltered()
    {
        return recordsFiltered;
    }
    
    public void setRecordsFiltered(Long value)
    {
        recordsFiltered = value;
    }
    
    public List<T> getData()
    {
        return data;
    }
    
    public void setData(List<T> value)
    {
        data = value;
    }
}