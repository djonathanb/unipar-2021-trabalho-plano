package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.application.exceptions.NegocioException
import br.unipar.plano.domain.centrais.usecases.RegistrarCobrancaUseCase
import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class RegistrarCobrancaUseCaseImpl(
    private val repository: CobrancaRepository,
    @Value("\${valor.adicional.cirurgia}")
    private val valorAdicionalCirurgia: BigDecimal,
    @Value("\${valor.adicional.consulta}")
    private val valorAdicionalConsulta: BigDecimal,
    @Value("\${dias.vencimento.cobranca}")
    private val diasVencimentoCobranca: Int
) : RegistrarCobrancaUseCase {

    override fun executa(contrato: Contrato, dataEmissao: LocalDate): Cobranca {
        if (verificaSeExisteCobrancaProContratoNoMes(contrato, dataEmissao)) {
            throw NegocioException(
                "Já existe cobrança em aberto para o contrato ${contrato.id} para o período ${
                    dataEmissao.format(
                        DateTimeFormatter.ISO_LOCAL_DATE
                    )
                }."
            )
        }
        if (dataEmissao.isAfter(LocalDate.now())) {
            throw NegocioException("Não é possível registrar uma Cobrança para uma data futura.")
        }
        val cobranca = Cobranca(
            id = IdCobranca(),
            valorContrato = calculaValorBase(contrato),
            valorAdicionalConsulta = valorAdicionalConsulta,
            valorAdicionalCirurgia = valorAdicionalCirurgia,
            valorAdicionalIdade = calculaAdicionalIdades(contrato),
            status = StatusCobranca.ABERTO,
            dataEmissao = dataEmissao,
            contrato = contrato,
            dataCancelamento = null,
            dataVencimento = geraDataVencimento(dataEmissao),
            valorTotal = BigDecimal.ZERO
        )
        return repository.save(cobranca)
    }

    fun verificaSeExisteCobrancaProContratoNoMes(contrato: Contrato, dataEmissao: LocalDate) =
        repository.existsInMonthByContratoAndDateAndStatusNotEquals(contrato, dataEmissao, StatusCobranca.CANCELADO)

    fun geraDataVencimento(dataEmissao: LocalDate) =
        dataEmissao.plusDays(diasVencimentoCobranca.toLong())

    fun calculaValorBase(contrato: Contrato) =
        contrato.dependentes.map { it.plano.valorBase }.fold(BigDecimal.ZERO, BigDecimal::add)

    fun calculaAdicionalIdades(contrato: Contrato) =
        BigDecimal.valueOf(contrato.dependentes.sumOf { it.idade() }.toDouble())
}