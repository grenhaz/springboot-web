package org.obarcia.springboot.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.obarcia.springboot.exceptions.ArticleNotFoundException;
import org.obarcia.springboot.exceptions.PageNotFoundException;
import org.obarcia.springboot.services.MailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Controlador para las Excepciones y errores.
 * 
 * @author obarcia
 */
@ControllerAdvice
public class ExceptionHandlingController
{
    /**
     * Excepción de artículo no encontrado.
     * @param ex Instancia de la excepción.
     * @return Vista resultante.
     */
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView actionArticleNotFound(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.article_not_found")
            .addObject("submessage", "")
            .addObject("exception", ex);
    }
    /**
     * Excepción de página no encontrada.
     * @param ex Instancia de la excepción.
     * @return Vista resultante.
     */
    @ExceptionHandler({PageNotFoundException.class,NoHandlerFoundException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView actionNoHandler(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.404")
            .addObject("submessage", "")
            .addObject("exception", ex);
    }
    /**
     * Excepción de acceso denegado.
     * @param ex Instancia de la excepción.
     * @return Vista resultante.
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public ModelAndView actionAccessDenied(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.404")
            .addObject("submessage", "")
            .addObject("exception", ex);
    }
    /**
     * Excepción de genérica de error interno.
     * @param ex Instancia de la excepción.
     * @return Vista resultante.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView actionInternalError(Exception ex)
    {
        Logger.getLogger(ExceptionHandlingController.class.getName()).log(Level.SEVERE, null, ex);
        
        return new ModelAndView("error")
            .addObject("message", "error.505")
            .addObject("exception", ex);
    }
}