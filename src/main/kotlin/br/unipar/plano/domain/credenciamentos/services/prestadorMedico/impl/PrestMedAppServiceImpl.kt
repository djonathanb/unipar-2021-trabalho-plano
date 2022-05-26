package br.unipar.plano.domain.credenciamentos.services.prestadorMedico.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.prestadorMedico.PrestMedAppService
import br.unipar.plano.domain.credenciamentos.services.prestadorMedico.PrestMedQueryService
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.DeletaPrestMedicoUseCase
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.CredenciaPrestMedicoUseCase
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.CriaPrestMedicoUseCase
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.DescredenciaPrestMedicoUseCase
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.AtualizaPrestMedicoUseCase
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.EspecialidadeDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoSummaryDTO
import org.springframework.stereotype.Service


@Service
class PrestMedAppServiceImpl(
    private val prestMedQueryService: PrestMedQueryService,
    private val criaPrestMedicoUseCase: CriaPrestMedicoUseCase,
    private val credenciaPrestMedicoUseCase: CredenciaPrestMedicoUseCase,
    private val descredenciaPrestMedicoUseCase: DescredenciaPrestMedicoUseCase,
    private val atualizaPrestMedicoUseCase: AtualizaPrestMedicoUseCase,
    private val deletaPrestMedicoUseCase: DeletaPrestMedicoUseCase
) : PrestMedAppService {

    override fun cria(prestadorMedicoDTO: PrestMedDTO): IdPrestadorMedico {
        val prestadorMedico = prestadorMedicoDTO.toModel(IdPrestadorMedico())
        val novoPrestadorMedico = criaPrestMedicoUseCase.executa(prestadorMedico)
        return novoPrestadorMedico.idPrestadorMedico
    }

    override fun atualiza(idPrestadorMedico: IdPrestadorMedico, prestadorMedicoDTO: PrestMedDTO) {
        atualizaPrestMedicoUseCase.executa(idPrestadorMedico) {
            it.with(
                nome = prestadorMedicoDTO.nome,
                crm = prestadorMedicoDTO.crm
               )
        }
    }

    override fun deleta(idPrestadorMedico: IdPrestadorMedico) {
        deletaPrestMedicoUseCase.executa(idPrestadorMedico)
    }

    override fun credenciar(idPrestadorMedico: IdPrestadorMedico) {
        credenciaPrestMedicoUseCase.executa(idPrestadorMedico)
    }

    override fun descredenciar(idPrestadorMedico: IdPrestadorMedico) {
        descredenciaPrestMedicoUseCase.executa(idPrestadorMedico)
    }

    override fun lista() = prestMedQueryService.lista().map(PrestadorMedicoSummaryDTO::toDTO)

    override fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedicoDetailsDTO {
        val prestadorMedico = prestMedQueryService.buscaPorId(idPrestadorMedico)
        return PrestadorMedicoDetailsDTO.toDTO(prestadorMedico)
    }

}











/*

    override fun lista(): List<PrestadorMedicoSummaryDTO> = prestMedQueryService.lista().map {
        toSummaryDTO(it)
    }

    override fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedicoDetailsDTO = toDetailsDTO(prestMedQueryService.buscaPorId(idPrestadorMedico))


    private fun toModel(id: IdPrestadorMedico, prestadorMedicoDTO: PrestMedDTO) = PrestadorMedico(
        id = id,
        nome = prestadorMedicoDTO.nome,
        crm = prestadorMedicoDTO.crm,
        especialidades = prestadorMedicoDTO.especialidades.map {especialidade -> Especialidade(id = IdPrestadorMedico() ,nomeEspecialidade = especialidade.nomeEspecialidade) },
        status = prestadorMedicoDTO.status
    )

    private fun toSummaryDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoSummaryDTO(
        id = prestadorMedico.id,
        nome = prestadorMedico.nome,
        crm = prestadorMedico.crm,
        especialidades = prestadorMedico.especialidades.map { especialidade -> SummaryEspDTO(nomeEspecialidade = especialidade.nomeEspecialidade) }
    )

    private fun toDetailsDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoDetailsDTO(
        id = prestadorMedico.id,
        prestMedData = toDTO(prestadorMedico)
    )

    private fun toDTO(prestadorMedico: PrestadorMedico) = PrestMedDTO(
        nome = prestadorMedico.nome,
        crm = prestadorMedico.crm,
        especialidades = prestadorMedico.especialidades.map{especialidade -> EspDTO(nomeEspecialidade = especialidade.nomeEspecialidade) },
        status = prestadorMedico.status
    )
*/