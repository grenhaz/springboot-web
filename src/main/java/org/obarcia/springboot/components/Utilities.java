package org.obarcia.springboot.components;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import static org.springframework.beans.BeanUtils.getPropertyDescriptor;

/**
 * Clase con utilidades varias.
 * 
 * @author obarcia
 */
public final class Utilities
{
    /**
     * Formato de fecha
     */
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Devuelve un texto aleatoria formado por valores hexadecimales.
     * @param numchars Número de caracteres.
     * @return Texto aleatorio.
     */
    public static String getRandomHexString(int numchars)
    {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
    /**
     * Devuelve el valor de una propiedad de una instancia BEAN.
     * @param bean Instancia.
     * @param property Nombre de la propiedad.
     * @return Valor de la propiedad o null si no la encuentra.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static Object getPropertyValue(Object bean, String property) 
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Class<?> beanClass = bean.getClass();
        PropertyDescriptor propertyDescriptor = getPropertyDescriptor(beanClass, property);
        if (propertyDescriptor == null) {
            return null;
        }

        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null) {
            return null;
        }
        
        return readMethod.invoke(bean);
    }
    /**
     * Devuelve el tiempo transcurrido.
     * @param dt Tiempo base,
     * @return Tiempo transcurrido o fecha.
     */
    public static String getElapsedTime(Date dt)
    {
        if (dt != null) {
            long ts = dt.getTime();
            long now = new Date().getTime();
            long diff = (now - ts) / 1000;
            if (diff > 24 * 60 * 60) {
                // Más de un día
                return FORMAT.format(dt);
            } else if (diff > 3600) {
                // Más de una 1 hora
                int value = (int) (diff / 3600);
                return value + " h";
            } else {
                // Minutos
                int value = (int) (diff / 60);
                return value + " m";
            }
        }
        
        return "";
    }
    /**
     * Hide constructor.
     */
    private Utilities()
    {
    }
}