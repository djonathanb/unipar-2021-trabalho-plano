package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.application.exceptions.NegocioException
import br.unipar.plano.domain.centrais.usecases.RegistrarCobrancaUseCase
import br.unipar.plano.domain.cobrancas.model.*
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.repository.ContratoRepository
import br.unipar.plano.domain.cobrancas.service.impl.ContratoNotFoundException
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class RegistrarCobrancaUseCaseImpl(
    private val repository: CobrancaRepository,
    private val contratoRepository: ContratoRepository,
    @Value("\${valor.adicional.cirurgia}")
    private val valorAdicionalCirurgia: BigDecimal,
    @Value("\${valor.adicional.consulta}")
    private val valorAdicionalConsulta: BigDecimal,
    @Value("\${dias.vencimento.cobranca}")
    private val diasVencimentoCobranca: Int
) : RegistrarCobrancaUseCase {

    override fun executa(
        idContrato: IdContrato,
        dataEmissao: LocalDate,
        cirurgias: Collection<Cirurgia>,
        procedimentos: Collection<Procedimento>
    ): Cobranca {
        if (verificaSeExisteCobrancaProContratoNoMes(idContrato, dataEmissao)) {
            throw NegocioException(
                "Já existe cobrança em aberto para o contrato ${idContrato.id} para o período ${
                    dataEmissao.format(
                        DateTimeFormatter.ISO_LOCAL_DATE
                    )
                }."
            )
        }
        if (dataEmissao.isAfter(LocalDate.now())) {
            throw NegocioException("Não é possível registrar uma Cobrança para uma data futura.")
        }
        //TODO ajustar esse trecho para buscar do QueryService do contrato quando este for entregue pela equipe 3
        val contrato = contratoRepository.findById(idContrato);
        if (contrato.isEmpty) {
            throw ContratoNotFoundException(idContrato);
        }
        val cobranca = Cobranca(
            id = IdCobranca(),
            valorContrato = calculaValorBase(contrato.get()),
            valorAdicionalConsulta = valorAdicionalConsulta,
            valorAdicionalCirurgia = valorAdicionalCirurgia,
            valorAdicionalIdade = calculaAdicionalIdades(contrato.get()),
            status = StatusCobranca.ABERTO,
            dataEmissao = dataEmissao,
            contrato = contrato.get(),
            dataCancelamento = null,
            dataVencimento = geraDataVencimento(dataEmissao),
            valorTotal = BigDecimal.ZERO,
            cirurgias = cirurgias,
            procedimentos = procedimentos
        )
        return repository.save(cobranca)
    }

    fun verificaSeExisteCobrancaProContratoNoMes(idContrato: IdContrato, dataEmissao: LocalDate) =
        repository.existsInMonthByContratoAndDateAndStatusNotEquals(idContrato, dataEmissao, StatusCobranca.CANCELADO)

    fun geraDataVencimento(dataEmissao: LocalDate) =
        dataEmissao.plusDays(diasVencimentoCobranca.toLong())

    fun calculaValorBase(contrato: Contrato) =
        contrato.dependentes.map { it.plano.valorBase }.fold(BigDecimal.ZERO, BigDecimal::add)

    fun calculaAdicionalIdades(contrato: Contrato) =
        BigDecimal.valueOf(contrato.dependentes.sumOf { it.idade() }.toDouble())
}