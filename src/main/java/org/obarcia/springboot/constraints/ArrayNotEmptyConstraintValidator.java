package org.obarcia.springboot.constraints;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.springboot.components.Utilities;

/**
 * Constraint Validator para el uso único de un nickname.
 * 
 * @author obarcia
 */
public class ArrayNotEmptyConstraintValidator implements ConstraintValidator<ArrayNotEmptyConstraint, Object>
{
    /**
     * Nombre del campo donde obtener el listado.
     * El error se incluirá en este campo.
     */
    private String fieldName;
    /**
     * Mensaje de error.
     */
    private String message;
    
    @Override
    public void initialize(ArrayNotEmptyConstraint c)
    {
        fieldName = c.field();
        message = c.message();
    }
    
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        try {
            final Object fieldValue = Utilities.getPropertyValue(candidate, fieldName);

            if (fieldValue != null) {
                if ((fieldValue instanceof List && !((List) fieldValue).isEmpty())
                	|| (fieldValue instanceof String && !((String) fieldValue).isEmpty())) {
                    return true;
                }
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(fieldName)
                        .addConstraintViolation();

                return false;
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        	// Omitted
        }
        
        return false;
    }
}