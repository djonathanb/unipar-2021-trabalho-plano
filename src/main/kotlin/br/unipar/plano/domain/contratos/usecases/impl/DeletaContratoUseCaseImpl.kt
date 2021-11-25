package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.centrais.model.ContratoRepository
import br.unipar.plano.domain.centrais.model.IdContrato
import br.unipar.plano.domain.contratos.usecases.DeletaContratosUseCase
import org.springframework.stereotype.Service

@Service
class DeletaContratoUseCaseImpl (private val contratoRepository: ContratoRepository): DeletaContratosUseCase {

    override fun executa(idContrato: IdContrato) {
        val contrato = contratoRepository.findById(idContrato).orElseThrow{ ContratoNotFoundException(idContrato) }
        return contratoRepository.delete(contrato)
    }
}