package br.unipar.plano.domain.contratos.services.impl

import br.unipar.plano.domain.contratos.cobrancas.service.CobrancaService
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.services.ContratoApplicationService
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.contratos.usecases.*
import br.unipar.plano.domain.contratos.planos.services.PlanoQueryService
import br.unipar.plano.interfaces.rest.contratos.cobrancas.CobrancaDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoSummaryDTO
import org.springframework.stereotype.Service
import java.util.*
import javax.validation.Valid


@Service
class ContratoApplicationServiceImpl(
    private val contratoQueryService: ContratoQueryService,
    private val criaContratoUseCase: CriaContratoUseCase,
    private val atualizaContratoUseCase: AtualizaContratoUseCase,
    private val cobrancaService: CobrancaService,
    private val cancelaContratoUseCase: CancelaContratoUseCase,
    private val planoQueryService: PlanoQueryService
) : ContratoApplicationService {

    override fun cria(@Valid contratoDTO: ContratoDTO): IdContrato {
        val contrato = contratoDTO.toModel(IdContrato())
        val novoContrato = criaContratoUseCase.cria(contrato)
        return novoContrato.id
    }

    override fun lista() = contratoQueryService.lista().map(ContratoSummaryDTO::toDTO)

    override fun buscaPorId(idContrato: IdContrato): ContratoDetailsDTO {
        val contrato = contratoQueryService.buscaPorId(idContrato)
        return ContratoDetailsDTO.toDTO(contrato)
    }

    override fun atualiza(idContrato: IdContrato, contratoDTO: ContratoDTO) {
        atualizaContratoUseCase.executa(idContrato) {
            it.with(
                dataContratoFinal = contratoDTO.dataContratoFinal,
                plano = contratoDTO.plano
            )
        }
    }

    override fun buscaPorPlano(idPlano: Plano) = contratoQueryService.buscaPorPlano(idPlano).map(ContratoDetailsDTO::toDTO)

    override fun buscaPorPlanoeStatus(
        plano: Plano,
        status: Optional<List<StatusContrato>>
    ): List<ContratoSummaryDTO> {
        return contratoQueryService.buscaPorPlanoeStatus(plano, status).map(ContratoSummaryDTO::toDTO)
    }

    override fun cancelaContrato(idContrato: IdContrato) {

        val contrato = contratoQueryService.buscaPorId(idContrato)
        val status : List<StatusCobranca> = listOf(StatusCobranca.ABERTO)
        val statusAberto: Optional<List<StatusCobranca>> = Optional.of(status)
        val listaCobranca: List<CobrancaDetailsDTO> = cobrancaService.buscarPorContratoAndStatus(contrato, statusAberto)

        return cancelaContratoUseCase.executa(idContrato, !listaCobranca.isNullOrEmpty())

    }
}

