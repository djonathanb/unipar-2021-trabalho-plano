package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.usecases.CriaContratoUseCase
import org.springframework.stereotype.Service

@Service
class CriaContratoUseCaseImpl (private val contratoRepository: ContratoRepository): CriaContratoUseCase {

    override fun cria(contrato: Contrato): Contrato {
        val listaContrato = contratoRepository.findByTitularAndStatus(contrato.titular, statusContrato = StatusContrato.ATIVO)
        if ( !listaContrato.isNullOrEmpty()) { throw ContratoJaExistenteException(contrato.titular) }

        if (contratoRepository.existsById(contrato.id)) {
            throw IllegalStateException("Já existe contrato cadastrado com a ID ${contrato.id}")
        }
        return contratoRepository.save(contrato)
    }
}