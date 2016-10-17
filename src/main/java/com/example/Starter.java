package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.example" })
public class Starter extends SpringBootServletInitializer
{
    //  TODO: use java8

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Starter.class, args);
    }
}