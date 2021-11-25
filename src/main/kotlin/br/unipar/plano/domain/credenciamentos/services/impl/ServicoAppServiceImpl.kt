package br.unipar.plano.domain.credenciamentos.services.impl


import br.unipar.plano.domain.credenciamentos.model.IdServico
import br.unipar.plano.domain.credenciamentos.model.Servico
import br.unipar.plano.domain.credenciamentos.services.ServicoAppService
import br.unipar.plano.domain.credenciamentos.services.ServicoQueryService
import br.unipar.plano.domain.credenciamentos.usecases.CriaServicoUseCase
import br.unipar.plano.interfaces.rest.credenciamentos.ServicoDTO
import br.unipar.plano.interfaces.rest.credenciamentos.ServicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.ServicoSummaryDTO
import org.springframework.stereotype.Service

@Service
class ServicoAppServiceImpl (
    private val servicoQueryService: ServicoQueryService,
    private val criaServicoUseCase: CriaServicoUseCase
): ServicoAppService {

    override fun buscaPorId(idServico: IdServico): ServicoDetailsDTO = toDetailsDTO(servicoQueryService.buscaPorId(idServico))

    override fun cria(servicoDTO: ServicoDTO): IdServico {
        val servico = toModel(IdServico(), servicoDTO)
        val novoServico = criaServicoUseCase.executa(servico)
        return novoServico.id
    }

    override fun lista(): List<ServicoSummaryDTO> = servicoQueryService.lista().map{
        toSummaryDTO(it)
    }


    private fun toModel(id: IdServico, servicoDTO: ServicoDTO) = Servico(
        id = id,
        servico = servicoDTO.servico
    )

    private fun toSummaryDTO(servico: Servico) = ServicoSummaryDTO(
        id = servico.id,
        servico = servico.servico
    )

    private fun toDetailsDTO(servico: Servico) = ServicoDetailsDTO(
        id = servico.id,
        servicoData = toDTO(servico)
    )

    private fun toDTO(servico: Servico) = ServicoDTO(
        servico =  servico.servico
    )

}