package org.obarcia.springboot.components.datatables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * DataTables: Petición.
 * 
 * @author obarcia
 */
public class DataTablesRequest
{
    /**
     * draw de la petición.
     */
    private Integer draw = 1;
    /**
     * Offset de inicio de los registros.
     */
    private Integer start = 0;
    /**
     * Total de registros.
     */
    private Integer length = 0;
    /**
     * Texto de búsqueda general.
     */
    private String search = null;
    /**
     * Listado de columnas indexadas por su número de columna.
     */
    private final HashMap<Integer, DataTablesColumn> columnsIndex = new HashMap<>();
    /**
     * Listado de columnas indexadas por su nombre de columna.
     */
    private final HashMap<String, DataTablesColumn> columns = new HashMap<>();
    /**
     * Listado de ordenaciones.
     */
    private final List<DataTablesOrder> order = new ArrayList<>();
    
    /**
     * Constructor de la clase.
     * @param request Instancia de la petición.
     */
    public DataTablesRequest(HttpServletRequest request)
    {
        setupRequest(request);
    }
    /**
     * Procesar los parámetros de la petición.
     * @param request Instancia de la petición.
     */
    private void setupRequest(HttpServletRequest request)
    {
        // Base
        draw = Integer.parseInt(request.getParameter("draw"));
        start = Integer.parseInt(request.getParameter("start"));
        length = Integer.parseInt(request.getParameter("length"));
        
        // Columnas
        String data;
        int i = 0;
        do {
            data = request.getParameter("columns[" + i + "][data]");
            if (data != null) {
                DataTablesColumn c = new DataTablesColumn();
                c.setData(data);
                c.setName(request.getParameter("columns[" + i + "][name]"));
                c.setSearchable(Boolean.valueOf(request.getParameter("columns[" + i + "][searchable]")));
                c.setOrderable(Boolean.valueOf(request.getParameter("columns[" + i + "][orderable]")));
                String se = request.getParameter("columns[" + i + "][search][value]");
                // XXX: Search regex
                Boolean seRegex = Boolean.valueOf(request.getParameter("columns[" + i + "][search][regex]"));
                c.setSearch(se);
                columnsIndex.put(i, c);
                columns.put(data, c);
            }
            i ++;
        } while(data != null);
        
        // Orden
        String column;
        i = 0;
        do {
            column = request.getParameter("order[" + i + "][column]");
            if (column != null) {
                DataTablesColumn dtc = columnsIndex.getOrDefault(Integer.parseInt(column), null);
                if (dtc != null) {
                    DataTablesOrder dto = new DataTablesOrder();
                    dto.setData(dtc.getName() != null && !dtc.getName().isEmpty() ? dtc.getName() : dtc.getData());
                    dto.setDir(request.getParameter("order[" + i + "][dir]").equals("asc") ? DataTablesOrder.ORDER_ASC : DataTablesOrder.ORDER_DESC);
                    order.add(dto);
                }
            }
            i ++;
        } while(column != null);
        
        // Search
        String se = request.getParameter("search[value]");
        // XXX: Search regex
        boolean seRegex = Boolean.valueOf(request.getParameter("search[regex]"));
        search = se;
    }
    /**
     * Devuelve si una columna tiene búsqueda o no.
     * @param name Nombre de la columna.
     * @return true si tiene búsqueda, false en caso contrario.
     */
    public boolean hasColumnSearch(String name)
    {
        return (columns.containsKey(name) && 
                columns.get(name).getSearchable().equals(Boolean.TRUE) && 
                columns.get(name).getSearch() != null &&
                !columns.get(name).getSearch().isEmpty());
    }
    /**
     * Devuelve el texto de la búsqueda de una columna.
     * @param name Nombre de la columna.
     * @return Texto de búsqueda o null si no lo tiene.
     */
    public String getColumnSearch(String name)
    {
        if (hasColumnSearch(name)) {
            return columns.get(name).getSearch();
        }
        
        return null;
    }
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Integer getDraw()
    {
        return draw;
    }
    public Integer getStart()
    {
        return start;
    }
    public Integer getLength()
    {
        return length;
    }
    public String getSearch()
    {
        return search;
    }
    public HashMap<String, DataTablesColumn> getColumns()
    {
        return columns;
    }
    public List<DataTablesOrder> getOrders()
    {
        return order;
    }
}