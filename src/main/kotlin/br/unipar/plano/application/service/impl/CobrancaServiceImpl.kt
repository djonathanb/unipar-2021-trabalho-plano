package br.unipar.plano.application.service.impl

import br.unipar.plano.application.exception.NegocioException
import br.unipar.plano.application.exception.RegistroNaoEncontradoException
import br.unipar.plano.application.service.interfaces.CobrancaService
import br.unipar.plano.domain.model.Cobranca
import br.unipar.plano.domain.model.Contrato
import br.unipar.plano.domain.valueobjects.StatusCobranca
import br.unipar.plano.repository.CobrancaRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class CobrancaServiceImpl(
    private val repository: CobrancaRepository,
    @Value("\${valor.adicional.cirurgia}")
    private val valorAdicionalCirurgia: BigDecimal,
    @Value("\${valor.adicional.consulta}")
    private val valorAdicionalConsulta: BigDecimal,
    @Value("\${dias.vencimento.cobranca}")
    private val diasVencimentoCobranca: Int
) : CobrancaService {
    override fun registrarCobranca(contrato: Contrato, dataEmissao: LocalDate): Cobranca {
        if (verificaSeExisteCobrancaProContratoNoMes(contrato, dataEmissao)) {
            throw NegocioException("Já existe cobrança em aberto para o contrato ${contrato.id} para o período ${dataEmissao.format(DateTimeFormatter.ISO_LOCAL_DATE)}.")
        }
        val cobranca = Cobranca(
            id = UUID.randomUUID(),
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

    override fun cancelarCobranca(cobranca: Cobranca): Cobranca {
        val cobrancaBanco = buscarPorId(cobranca.id)
        cobrancaBanco.cancelar()
        return repository.save(cobrancaBanco)
    }

    override fun buscaTodos(): MutableIterable<Cobranca> = repository.findAll()

    override fun buscarPorId(id: UUID): Cobranca {
        try {
           return repository.findById(id)
        } catch (exception: NoSuchElementException) {
            throw RegistroNaoEncontradoException()
        }
    }

    fun geraDataVencimento(dataEmissao: LocalDate) =
        dataEmissao.plusDays(diasVencimentoCobranca.toLong())

    fun verificaSeExisteCobrancaProContratoNoMes(contrato: Contrato, dataEmissao: LocalDate) = repository.existsInMonthByContratoAndByDateAndByStatusNotEquals(contrato, dataEmissao, StatusCobranca.CANCELADO)

    fun calculaValorBase(contrato: Contrato) =
        contrato.dependentes.map { it.plano.valorBase }.fold(BigDecimal.ZERO, BigDecimal::add)

    fun calculaAdicionalIdades(contrato: Contrato) = BigDecimal.valueOf(contrato.dependentes.sumOf { it.idade() }.toDouble())
}
