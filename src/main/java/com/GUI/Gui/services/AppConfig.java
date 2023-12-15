package com.GUI.Gui.services;

import functions.factory.ArrayTabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ArrayTabulatedFunctionFactory createFunctions() {
        return new ArrayTabulatedFunctionFactory();
    }

}
