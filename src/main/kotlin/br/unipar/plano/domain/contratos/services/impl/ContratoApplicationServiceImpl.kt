package br.unipar.plano.domain.contratos.services.impl

import br.unipar.plano.domain.contratos.cobrancas.service.CobrancaService
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.services.ContratoApplicationService
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.contratos.usecases.*
import br.unipar.plano.domain.contratos.usecases.impl.ContratoJaExistenteException
import br.unipar.plano.domain.contratos.usecases.impl.ContratoPendenciaException
import br.unipar.plano.interfaces.rest.contratos.cobrancas.CobrancaDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoSummaryDTO
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import javax.validation.Valid


@Service
class ContratoApplicationServiceImpl(
    private val contratoQueryService: ContratoQueryService,
    private val criaContratoUseCase: CriaContratoUseCase,
    private val atualizaContratoUseCase: AtualizaContratoUseCase,
    private val cobrancaService: CobrancaService,
    private val cancelaContratoUseCase: CancelaContratoUseCase
) : ContratoApplicationService {

    override fun cria(@Valid contratoDTO: ContratoDTO): IdContrato {
        val contrato = contratoDTO.toModel(IdContrato())

        val contratosAtivos = contratoQueryService.findByTitularAndStatus(contrato.titular, StatusContrato.ATIVO)

        if (Objects.nonNull(contratosAtivos) && contratosAtivos.isNotEmpty()) {
          throw ContratoJaExistenteException(contrato.titular)
        }

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
                plano = contratoDTO.idPlano
            )
        }
    }

    override fun buscaPorPlano(idPlano: Plano) = contratoQueryService.buscaPorPlano(idPlano).map(ContratoDetailsDTO::toDTO)


    override fun cancelaContrato(idContrato: IdContrato) {
        val contrato = contratoQueryService.buscaPorId(idContrato)
        val status : List<StatusCobranca> = listOf(StatusCobranca.ABERTO)
        val teste: Optional<List<StatusCobranca>> = Optional.of(status)
        val listaCobranca: List<CobrancaDetailsDTO> = cobrancaService.buscarPorContratoAndStatus(contrato, teste)

        if (contrato.dataContratacao.isAfter(LocalDate.now().minusDays(90)) or listaCobranca.isEmpty()) {
            ContratoPendenciaException(contrato.titular);
        }else {
            cancelaContratoUseCase.executa(idContrato)
        }

    }
}

