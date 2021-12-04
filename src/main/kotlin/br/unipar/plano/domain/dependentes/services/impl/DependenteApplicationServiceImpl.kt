package br.unipar.plano.domain.dependentes.services.impl


import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.services.DependenteApplicationService
import br.unipar.plano.domain.dependentes.services.DependenteQueryService
import br.unipar.plano.domain.dependentes.usecase.CadastraDependenteUseCase
import br.unipar.plano.domain.dependentes.usecase.DeletaDependenteUseCase
import br.unipar.plano.interfaces.rest.dependentes.DependenteDTO
import br.unipar.plano.interfaces.rest.dependentes.DependenteDetailsDTO
import org.springframework.stereotype.Service
import javax.validation.Valid

@Service
class DependenteApplicationServiceImpl(
    private val dependenteQueryService: DependenteQueryService,
    private val criaDependenteUseCase: CadastraDependenteUseCase,
    private val deletaDependenteUseCase: DeletaDependenteUseCase,
    private val contratoQueryService: ContratoQueryService
) : DependenteApplicationService {

    override fun cria(@Valid dependenteDTO: DependenteDTO): IdDependente {
        val dependente = dependenteDTO.toModel((IdDependente()), dependenteDTO.pessoa)
        val novoDependente = criaDependenteUseCase.cadastra(dependente)
        return novoDependente.idDependente
    }

    override fun buscaPorContrato(idContrato: IdContrato): List<DependenteDetailsDTO> {
        val contrato = contratoQueryService.buscaPorId(idContrato)
        return dependenteQueryService.buscaPorContrato(contrato).map(DependenteDetailsDTO::toDTO)
    }

    override fun buscaPorId(idDependente: IdDependente): DependenteDetailsDTO {
        val dependente = dependenteQueryService.buscaPorId(idDependente)
        return DependenteDetailsDTO.toDTO(dependente)
    }

    override fun deleta(idDependente: IdDependente) {
        deletaDependenteUseCase.executa(idDependente)
    }

}