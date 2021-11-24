package br.unipar.plano.domain.credenciamentos.services.impl

import br.unipar.plano.domain.credenciamentos.model.Especialidade
import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.PrestMedAppService
import br.unipar.plano.domain.credenciamentos.services.PrestMedQueryService
import br.unipar.plano.domain.credenciamentos.usecases.CriaPrestMedicoUseCase
import br.unipar.plano.interfaces.rest.credenciamentos.EspDTO
import br.unipar.plano.interfaces.rest.credenciamentos.PrestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.PrestadorMedicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.PrestadorMedicoSummaryDTO
import org.springframework.stereotype.Service


@Service
class PrestMedAppServiceImpl(
    private val prestMedQueryService: PrestMedQueryService,
    private val criaPrestMedicoUseCase: CriaPrestMedicoUseCase
) : PrestMedAppService {

    override fun cria(prestadorMedicoDTO: PrestMedDTO): IdPrestadorMedico{
        val prestadorMedico = toModel(IdPrestadorMedico(),prestadorMedicoDTO)
        val novoPrestadorMedico = criaPrestMedicoUseCase.executa(prestadorMedico)
        return novoPrestadorMedico.id
    }

    override fun lista(): List<PrestadorMedicoSummaryDTO> = prestMedQueryService.lista().map {
        toSummaryDTO(it)
    }

    override fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedicoDetailsDTO = toDetailsDTO(prestMedQueryService.buscaPorId(idPrestadorMedico))



    private fun toModel(id: IdPrestadorMedico, prestadorMedicoDTO: PrestMedDTO) = PrestadorMedico(
        id = id,
        nome = prestadorMedicoDTO.nome,
        crm = prestadorMedicoDTO.crm,
        //especialidades = listOf(prestadorMedicoDTO.especialidade),
        status = prestadorMedicoDTO.status
    )

    private fun toSummaryDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoSummaryDTO(
        id = prestadorMedico.id,
        nome = prestadorMedico.nome,
        crm = prestadorMedico.crm,
        status = prestadorMedico.status,
       // especialidades = listOf(prestadorMedico.especialidade)
    )

    private fun toDetailsDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoDetailsDTO(
        id = prestadorMedico.id,
        prestMedData = toDTO(prestadorMedico)
    )

    private fun toDTO(prestadorMedico: PrestadorMedico) = PrestMedDTO(
        nome = prestadorMedico.nome,
        crm = prestadorMedico.crm,
        //especialidades = listOf(prestadorMedico.especialidade),
        status = prestadorMedico.status
    )

}