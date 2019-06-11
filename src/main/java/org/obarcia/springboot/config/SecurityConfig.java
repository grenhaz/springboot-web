package org.obarcia.springboot.config;

import org.obarcia.springboot.services.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase con la configuración de seguridad.
 * 
 * @author obarcia
 */
// Fichero de configuración
@Configuration
// Seguridad web
@EnableWebSecurity
// Habilitado @Secured de manera global
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/data/**").permitAll() 
                .antMatchers("/resources/**").permitAll() 
                .antMatchers("/**").permitAll() 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .failureUrl("/user/login?error=true")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/user/login")
                .permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
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