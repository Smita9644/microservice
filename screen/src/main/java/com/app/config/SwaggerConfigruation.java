package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This configuration is for swagger ui.
 *
 */
@EnableSwagger2
@Configuration
@Component
public class SwaggerConfigruation  extends WebMvcConfigurerAdapter  {
	
	/**
	 * This method will configure all controllers with end points and connect that with SWAGGER-UI.
	 */
	@Bean
	public Docket api() {
		/**
		 * This method will configure all controllers with end points and connect that with SWAGGER-UI.
		 */
      return new Docket(DocumentationType.SWAGGER_2)  
              .select()//Return the object of ApiSelectorBuilder                        
              .apis(RequestHandlerSelectors.any())//this will handle any requests in swagger.          
              .paths(PathSelectors.any())                      
              .build();      
        
	}
	
}
