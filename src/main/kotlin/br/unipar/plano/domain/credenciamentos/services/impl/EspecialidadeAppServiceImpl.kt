package br.unipar.plano.domain.credenciamentos.services.impl

import br.unipar.plano.domain.credenciamentos.model.*
import br.unipar.plano.domain.credenciamentos.services.EspecialidadeAppService
import br.unipar.plano.domain.credenciamentos.services.EspecialidadeQueryService
import br.unipar.plano.domain.credenciamentos.usecases.CriaEspecialidadeUseCase
import br.unipar.plano.interfaces.rest.credenciamentos.EspDTO
import br.unipar.plano.interfaces.rest.credenciamentos.EspecialidadeDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.EspecialidadeSummaryDTO
import org.springframework.stereotype.Service

@Service
class EspecialidadeAppServiceImpl (
    private val especialidadeQueryService: EspecialidadeQueryService,
    private val criaEspecialidadeUseCase: CriaEspecialidadeUseCase
        ): EspecialidadeAppService{

    override fun buscaPorId(idEspecialidade: IdEspecialidade): EspecialidadeDetailsDTO = toDetailsDTO(especialidadeQueryService.buscaPorId(idEspecialidade))

    override fun cria(espDTO: EspDTO): IdEspecialidade {
        val especialidade = toModel(IdEspecialidade(), espDTO)
        val novaEspecialidade = criaEspecialidadeUseCase.executa(especialidade)
        return novaEspecialidade.id
    }

    override fun lista(): List<EspecialidadeSummaryDTO> = especialidadeQueryService.lista().map{
        toSummaryDTO(it)
    }


    private fun toModel(id: IdEspecialidade, espDTO: EspDTO) = Especialidade(
        id = id,
        nomeEspecialidade = espDTO.nomeEspecialidade
    )

    private fun toSummaryDTO(especialidade: Especialidade) = EspecialidadeSummaryDTO(
        id = especialidade.id,
        nomeEspecialidade = especialidade.nomeEspecialidade
    )

    private fun toDetailsDTO(especialidade: Especialidade) = EspecialidadeDetailsDTO(
        id = especialidade.id,
        especialidadeData = toDTO(especialidade)
    )

    private fun toDTO(especialidade: Especialidade) = EspDTO(
        nomeEspecialidade =  especialidade.nomeEspecialidade
    )

        }