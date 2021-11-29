package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.usecases.CancelaContratoUseCase

class CancelaContratoUseCaseImpl (private val contratoRepository: ContratoRepository) : CancelaContratoUseCase {

    override fun executa(idContrato: IdContrato) {
        val central = contratoRepository.findById(idContrato).orElseThrow { ContratoNotFoundException(idContrato) }
        contratoRepository.save(central.cancela())
    }

}