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
    private int draw = 1;
    /**
     * Total de registros.
     */
    private int recordsTotal = 0;
    /**
     * Total de registros filtrados.
     */
    private int recordsFiltered = 0;
    /**
     * Registros.
     */
    private List<T> data;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public int getDraw()
    {
        return draw;
    }
    public void setDraw(int value)
    {
        draw = value;
    }
    public int getRecordsTotal()
    {
        return recordsTotal;
    }
    public void setRecordsTotal(int value)
    {
        recordsTotal = value;
    }
    public int getRecordsFiltered()
    {
        return recordsFiltered;
    }
    public void setRecordsFiltered(int value)
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