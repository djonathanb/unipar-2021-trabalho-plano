package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.application.exceptions.NegocioException
import br.unipar.plano.domain.centrais.usecases.RegistrarCobrancaUseCase
import br.unipar.plano.domain.cobrancas.commands.RegistrarCobrancaCommand
import br.unipar.plano.domain.cobrancas.gateway.CobrancaGateway
import br.unipar.plano.domain.cobrancas.model.*
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.service.impl.ContratoNotFoundException
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.infra.cobrancas.repository.ContratoRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class RegistrarCobrancaUseCaseImpl(
    private val gateway: CobrancaGateway,
    private val queryService: CobrancaQueryService,
    private val contratoRepository: ContratoRepository,
    @Value("\${cobranca.valor.adicional.cirurgia}")
    private val valorAdicionalCirurgia: BigDecimal,
    @Value("\${cobranca.valor.adicional.consulta}")
    private val valorAdicionalConsulta: BigDecimal,
    @Value("\${cobranca.dias.vencimento}")
    private val diasVencimentoCobranca: Int
) : RegistrarCobrancaUseCase {

    override fun executa(
        command: RegistrarCobrancaCommand
    ): Cobranca {
        if (verificaSeExisteCobrancaProContratoNoMes(command.idContrato, command.dataEmissao)) {
            throw NegocioException(
                "Já existe cobrança em aberto para o contrato ${command.idContrato.id} para o período ${
                    command.dataEmissao.format(
                        DateTimeFormatter.ISO_LOCAL_DATE
                    )
                }."
            )
        }
        if (command.dataEmissao.isAfter(LocalDate.now())) {
            throw NegocioException("Não é possível registrar uma Cobrança para uma data futura.")
        }
        //TODO ajustar esse trecho para buscar do QueryService do contrato quando este for entregue pela equipe 3
        val contrato = contratoRepository.findById(command.idContrato)
        if (contrato.isEmpty) {
            throw ContratoNotFoundException(command.idContrato)
        }
        val cobranca = Cobranca(
            id = IdCobranca(),
            valorContrato = calculaValorBase(contrato.get()),
            valorAdicionalConsulta = valorAdicionalConsulta,
            valorAdicionalCirurgia = valorAdicionalCirurgia,
            valorAdicionalIdade = calculaAdicionalIdades(contrato.get()),
            status = StatusCobranca.ABERTO,
            dataEmissao = command.dataEmissao,
            contrato = contrato.get(),
            dataCancelamento = null,
            dataVencimento = geraDataVencimento(command.dataEmissao),
            valorTotal = BigDecimal.ZERO,
            cirurgias = command.cirurgias,
            procedimentos = command.procedimentos
        )
        queryService.salvar(cobranca.toCobrancaView())
        return gateway.salvar(cobranca)
    }

    fun verificaSeExisteCobrancaProContratoNoMes(idContrato: IdContrato, dataEmissao: LocalDate) =
        queryService.verificaSeExisteCobrancaProContratoNoMes(idContrato, dataEmissao, StatusCobranca.CANCELADO)

    fun geraDataVencimento(dataEmissao: LocalDate) =
        dataEmissao.plusDays(diasVencimentoCobranca.toLong())

    fun calculaValorBase(contrato: Contrato) =
        contrato.dependentes.map { it.plano.valorBase }.fold(BigDecimal.ZERO, BigDecimal::add)

    fun calculaAdicionalIdades(contrato: Contrato) =
        BigDecimal.valueOf(contrato.dependentes.sumOf { it.idade() }.toDouble())

}