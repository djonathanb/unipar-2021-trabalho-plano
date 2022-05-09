package br.unipar.plano

import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.model.Usuario
import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.infra.cobrancas.repository.ContratoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootApplication
class Unipar2021TrabalhoPlanoApplication {

    @Bean
    @ConditionalOnExpression("\${spring.h2.console.enabled:true} and \${inserir.contrato.startup:true}")
    //objetivo é só salvar um contrato para ser possível testar a rotina de cobrança quando estiver utilizando banco em memória
    fun insereContratoNoStartup(contratoRepository: ContratoRepository) = CommandLineRunner {
        contratoRepository.save(
            Contrato(
                id = IdContrato(UUID.fromString("89d96d4c-5496-11ec-bf63-0242ac130002")),
                dependentes = mutableListOf(
                    Usuario(
                        id = UUID.fromString("8ed0dd32-e8de-420b-9566-e3df4969aa38"),
                        plano = br.unipar.plano.domain.planos.model.Plano(
                            id = IdPlano(UUID.fromString("18ac3b92-b3c1-4145-84d4-21fa8e48c4ee")),
                            valorBase = BigDecimal.valueOf(500),
                            nome = "Plano MOCK"
                        ),
                        dataNascimento = LocalDate.of(1997, 11, 6)
                    )
                )
            )
        )

    }
}

fun main(args: Array<String>) {
    runApplication<Unipar2021TrabalhoPlanoApplication>(*args)
}
