package org.obarcia.springboot.constraints;

import java.lang.reflect.InvocationTargetException;
import javax.management.IntrospectionException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.springboot.components.Utilities;

/**
 * Constraint Validator para que 2 campos sean iguales.
 * 
 * @author obarcia
 */
public class FieldEqualConstraintValidator implements ConstraintValidator<FieldEqualConstraint, Object>
{
    /**
     * Nombre del primer campo (Se incluirá el error en este).
     */
    private String firstFieldName;
    /**
     * Nombre del segundo campo.
     */
    private String secondFieldName;
    /**
     * Mensaje de error.
     */
    private String message;

    @Override
    public void initialize(FieldEqualConstraint c)
    {
        firstFieldName = c.first();
        secondFieldName = c.second();
        message = c.message();
    }
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        try {
            final Object firstObj = Utilities.getPropertyValue(candidate, firstFieldName);
            final Object secondObj = Utilities.getPropertyValue(candidate, secondFieldName);
            if (firstObj != null && secondObj != null && firstObj.equals(secondObj)) {
                return true;
            } else {
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(firstFieldName)
                        .addConstraintViolation();

                return false;
            }
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {}
        
        return true;
    }
}