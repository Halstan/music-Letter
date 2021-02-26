package com.musicletter.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(
                RequestHandlerSelectors
                    .basePackage("com.musicletter.app.controller")
            )
            .paths(PathSelectors.ant("/**"))
            .build()
            .apiInfo(apiDetail())
    }

    private fun apiDetail(): ApiInfo {
        return ApiInfo(
            "Music Letter API documentation",
            "Documentación para la API de Music Letter",
            "1.0",
            "Project - Free to use",
            Contact("Music Letter", "https://github.com/Halstan/musicLetter", "enzoarauco@gmail.com"),
            "MIT License",
            "", emptyList()
        )
    }

}