package co.com.mezubo.carrillo.roulette.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Punto de configuración para generar Swagger
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TITLLE_BUSINESS = "MEZUBO - TEST YAHER CARRILLO";
    private static final String DESCRIPTION_BUSINESS = "Componente transversal que la interaccion con juego de ruleta. \n\n" +
            "Tecnologias:" +
            "1.-) Java: 11\n" +
            "2.-) Spring-Boot: 2.3.0.RELEASE\n" +
            "3.-) Encoding: UTF8\n" +
            "4.-) URL PROXY - GITHUB: https://github.com/yahercarrillo/mezubo\n" +
            "6.-) Contacto - yahercarrillo@gmail.com\n";

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(TITLLE_BUSINESS)
                .description(DESCRIPTION_BUSINESS)
                .version("1.0.0")
                .build();
    }


}
