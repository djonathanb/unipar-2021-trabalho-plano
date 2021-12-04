package br.unipar.plano.domain.dependentes.usecase.impl

import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.DependenteRepository
import br.unipar.plano.domain.dependentes.usecase.CadastraDependenteUseCase
import org.springframework.stereotype.Service

@Service
class CadastraDependenteUseCaseImpl(private val dependenteRepository: DependenteRepository): CadastraDependenteUseCase{

    override fun cadastra(dependente: Dependente): Dependente {
        if (dependenteRepository.existsById(dependente.idDependente)) {
            throw IllegalStateException("Outra Central com id ${dependente.idDependente} já foi cadastrada")
        }
        return dependenteRepository.save(dependente)
    }


}