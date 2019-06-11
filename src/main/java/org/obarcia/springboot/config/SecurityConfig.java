package org.obarcia.springboot.config;

import org.obarcia.springboot.services.UserAccessService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Clase con la configuración de seguridad.
 * 
 * @author obarcia
 */
// Fichero de configuración
@Configuration
// Habilitado el sistema de seguridad
@EnableWebSecurity
// Habilitado @Secured de manera global
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    /**
     * Validación del usuario.
     * @return Instancia del usuario o null en caso de no ser válido.
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService()
    {
        return new UserAccessService();
    }
}