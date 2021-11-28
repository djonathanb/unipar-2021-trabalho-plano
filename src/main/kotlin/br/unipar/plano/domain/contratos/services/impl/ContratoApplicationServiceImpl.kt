package br.unipar.plano.domain.contratos.services.impl

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.Plano
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.services.ContratoApplicationService
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.contratos.usecases.*
import br.unipar.plano.domain.contratos.usecases.impl.ContratoJaExistenteException
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
    private val deletaContratosUseCase: DeletaContratosUseCase,
    private val atualizaContratoUseCase: AtualizaContratoUseCase
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

    override fun deleta(idContrato: IdContrato) {
        deletaContratosUseCase.executa(idContrato)
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
                idPlano = contratoDTO.idPlano
            )
        }
    }

    override fun buscaPorPlano(idPlano: Plano) = contratoQueryService.buscaPorPlano(idPlano).map(ContratoDetailsDTO::toDTO)
}