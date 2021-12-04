package br.unipar.plano.domain.dependentes.services.impl

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.DependenteRepository
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.services.DependenteQueryService
import br.unipar.plano.domain.dependentes.usecase.impl.DependenteNotFoundException
import org.springframework.stereotype.Service

@Service
class DependenteQueryServiceImpl(private val dependenteRepository: DependenteRepository): DependenteQueryService {

    override fun lista(): List<Dependente> = dependenteRepository.findAll()

    override fun buscaPorId(idDependente : IdDependente): Dependente = dependenteRepository.findById(idDependente).orElseThrow{
        throw DependenteNotFoundException(idDependente)
    }

    override fun buscaPorContrato(contrato: Contrato): List<Dependente> {
       return dependenteRepository.findByContrato(contrato)
    }

}