package br.unipar.plano.domain.contratos.usecases.impl


import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.usecases.AtualizaContratoUseCase
import org.springframework.stereotype.Service

@Service
class AtualizaContratoUseCaseImpl(private val contratoRepository: ContratoRepository) : AtualizaContratoUseCase {

    override fun executa(idContrato: IdContrato, transformation: (Contrato) -> Contrato) {
        val contrato = contratoRepository.findById(idContrato).orElseThrow { ContratoNotFoundException(idContrato) }
        contratoRepository.save(transformation(contrato).with(id = idContrato))
    }
}