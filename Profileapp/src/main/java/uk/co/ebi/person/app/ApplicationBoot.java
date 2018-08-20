package uk.co.ebi.person.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import uk.co.ebi.person.datastore.DataStore;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "uk.co.ebi.person")
@EnableSwagger2
public class ApplicationBoot {

	@Bean(name="dataStore") 
	public DataStore getDataStore(){
		return new DataStore();
	}
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        .select().apis(RequestHandlerSelectors.basePackage("uk.co.ebi.person.controller"))
        .paths(regex("/api.*"))
        .build();
   }
		
	public static void main(String[] args) {
		SpringApplication.run(ApplicationBoot.class, args);
	}
}
