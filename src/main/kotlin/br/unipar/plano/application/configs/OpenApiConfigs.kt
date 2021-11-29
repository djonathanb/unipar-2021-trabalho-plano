package br.unipar.plano.application.configs

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfigs {

    @Bean
    fun openApi() = OpenAPI().info(
        Info()
            .title("Planos API - Unipar")
            .description("API de exemplo implementada como trabalho da disciplina de Desenvolvimento BackEnd")
            .version("1.0.0")
    ).externalDocs(
        ExternalDocumentation()
            .description("Projeto Github")
            .url("https://github.com/djonathanb/unipar-2021-trabalho-plano")
    )
}