package com.app.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   @Bean
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
         .apiInfo(apiInfo())
         .securityContexts(List.of(securityConfig()))
         .securitySchemes(List.of(apiKey()))
         .select()
         .apis(RequestHandlerSelectors.basePackage("com.app.movie.ports.inputs.controllers"))
         .paths(PathSelectors.any())
         .build();
   }

   private ApiKey apiKey() {
      return new ApiKey("JWT", "Authorization", "header");
   }

   private SecurityContext securityConfig() {
      return SecurityContext.builder().securityReferences(defaultAuthentication()).build();
   }

   private List<SecurityReference> defaultAuthentication() {
      AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      return List.of(new SecurityReference("JWT", authorizationScopes));
   }

   private ApiInfo apiInfo() {
      return new ApiInfo(
         "Disney Api REST",
         "Application to create movies and characters from the Disney world.",
         "1.0",
         "Own application",
         new Contact("Cristian Aguirre", "https://github.com/Cristianaaguirre", "aguirre.cristian.alberto1@gmail.com"),
         "License of API", "API license URL", Collections.emptyList());
   }
}
