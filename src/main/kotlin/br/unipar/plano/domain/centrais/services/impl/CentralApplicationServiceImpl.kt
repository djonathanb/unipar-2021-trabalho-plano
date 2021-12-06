package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.domain.centrais.services.CentralQueryService
import br.unipar.plano.domain.centrais.usecases.*
import br.unipar.plano.interfaces.rest.centrais.dto.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.dto.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.dto.CentralSummaryDTO
import org.springframework.stereotype.Service

@Service
class CentralApplicationServiceImpl(
    private val centralQueryService: CentralQueryService,
    private val criaCentralUseCase: CriaCentralUseCase,
    private val atualizaCentralUseCase: AtualizaCentralUseCase,
    private val deletaCentralUseCase: DeletaCentralUseCase,
    private val credenciaCentralUseCase: CredenciaCentralUseCase,
    private val descredenciaCentralUseCase: DescredenciaCentralUseCase
) : CentralApplicationService {

    override fun cria(centralDTO: CentralDTO): IdCentral {
        val central = centralDTO.toModel(IdCentral())
        val novaCentral = criaCentralUseCase.executa(central)
        return novaCentral.id
    }

    override fun atualiza(idCentral: IdCentral, centralDTO: CentralDTO) {
        atualizaCentralUseCase.executa(idCentral) {
            it.with(
                nome = centralDTO.nome,
                cnpj = centralDTO.cnpj,
                endereco = centralDTO.endereco.toModel(idCentral)
            )
        }
    }

    override fun deleta(idCentral: IdCentral) {
        deletaCentralUseCase.executa(idCentral)
    }

    override fun credenciar(idCentral: IdCentral) {
        credenciaCentralUseCase.executa(idCentral)
    }

    override fun descredenciar(idCentral: IdCentral) {
        descredenciaCentralUseCase.executa(idCentral)
    }

    override fun lista() = centralQueryService.lista().map(CentralSummaryDTO::toDTO)

    override fun buscaPorId(idCentral: IdCentral): CentralDetailsDTO {
        val central = centralQueryService.buscaPorId(idCentral)
        return CentralDetailsDTO.toDTO(central)
    }

}