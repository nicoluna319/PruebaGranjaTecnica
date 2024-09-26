package onCredit.panacaGranja.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
title = "API para administrar Granja Panaca Nicolas Luna Proyecto Para Oncredit",
version ="1.0",
description = "Documentacion Api de Granja Panaca"))
public class OpenApiConfig {
    
}