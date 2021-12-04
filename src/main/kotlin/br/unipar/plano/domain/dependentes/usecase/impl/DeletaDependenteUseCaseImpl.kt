package br.unipar.plano.domain.dependentes.usecase.impl

import br.unipar.plano.domain.centrais.usecases.impl.CentralNotFoundException
import br.unipar.plano.domain.dependentes.model.DependenteRepository
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.usecase.DeletaDependenteUseCase
import org.springframework.stereotype.Service

@Service
class DeletaDependenteUseCaseImpl(private val dependenteRepository: DependenteRepository): DeletaDependenteUseCase {

    override fun executa(idDependente: IdDependente) {
        val central = dependenteRepository.findById(idDependente).orElseThrow { DependenteNotFoundException(idDependente) }
        return dependenteRepository.delete(central)
    }



}