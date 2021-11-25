package br.unipar.plano.domain.credenciamentos.usecases.impl

import br.unipar.plano.domain.credenciamentos.model.Servico
import br.unipar.plano.domain.credenciamentos.model.ServicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.CriaServicoUseCase
import org.springframework.stereotype.Service

@Service
class CriaServicoUseCaseImpl(private val servicoRepository: ServicoRepository) : CriaServicoUseCase {

    override fun executa(servico: Servico): Servico {
        return servicoRepository.save(servico)
    }
}