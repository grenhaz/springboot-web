package org.obarcia.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// TODO: Optimizar el war
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringbootApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}