package org.obarcia.springboot.components.datatables;

/**
 * Datatables: Ordenación.
 * 
 * @author obarcia
 */
public class DataTablesOrder
{
    public static final int ORDER_ASC = 0;
    public static final int ORDER_DESC = 1;
    
    /**
     * Nombre de la columna.
     */
    private String data;
    /**
     * Dirección de ordenación.
     */
    private Integer dir;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public String getData()
    {
        return data;
    }
    public void setData(String value)
    {
        data = value;
    }
    public Integer getDir()
    {
        return dir;
    }
    public void setDir(Integer value)
    {
        dir = value;
    }
}