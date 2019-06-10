package org.obarcia.springboot.components.datatables;

/**
 * Dtatables: Columna.
 * 
 * @author obarcia
 */
public class DataTablesColumn
{
    /**
     * Nombre de la columna.
     */
    private String data;
    /**
     * Nombre alternativo de la columna.
     */
    private String name;
    /**
     * Si se puede realizar búsquedas.
     */
    private Boolean searchable;
    /**
     * Si se puede ordenar.
     */
    private Boolean orderable;
    /**
     * Texto de búsqueda.
     */
    private String search;
    
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
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }
    public Boolean getSearchable()
    {
        return searchable;
    }
    public void setSearchable(Boolean value)
    {
        searchable = value;
    }
    public Boolean getOrderable()
    {
        return orderable;
    }
    public void setOrderable(Boolean value)
    {
        orderable = value;
    }
    public String getSearch()
    {
        return search;
    }
    public void setSearch(String value)
    {
        search = value;
    }
}