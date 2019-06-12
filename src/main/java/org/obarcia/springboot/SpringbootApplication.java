package org.obarcia.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// TODO: Optimizar el war
// TODO: No se accede a la parte de administración.
// TODO: Añadir comentarios que falten
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringbootApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}