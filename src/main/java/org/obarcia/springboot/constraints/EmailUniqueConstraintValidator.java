package org.obarcia.springboot.constraints;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.springboot.components.Utilities;
import org.obarcia.springboot.models.entity.user.User;
import org.obarcia.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Constraint Validator para un email único.
 * 
 * @author obarcia
 */
public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUniqueConstraint, Object>
{
    /**
     * Instancia del servicio de usuarios.
     */
    @Autowired
    private UserService userService;
    
    /**
     * Nombre del campo donde obtener el email.
     * El error se incluirá en este campo.
     */
    private String fieldName;
    /**
     * Mensaje de error.
     */
    private String message;

    @Override
    public void initialize(EmailUniqueConstraint c) {
        fieldName = c.field();
        message = c.message();
    }
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        try {
            final Object id = Utilities.getPropertyValue(candidate, "id");
            final Object email = Utilities.getPropertyValue(candidate, fieldName);

            User user = userService.getUserByEmail((String) email);
            if  (user == null || (id != null && user.getId().equals(id))) {
                return true;
            } else {
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(fieldName)
                        .addConstraintViolation();

                return false;
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        }
        
        return false;
    }
}