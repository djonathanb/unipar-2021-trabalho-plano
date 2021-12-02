package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.usecases.CancelaContratoUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class CancelaContratoUseCaseImpl (private val contratoRepository: ContratoRepository) : CancelaContratoUseCase {

    override fun executa(idContrato: IdContrato, cobrancaEmAberto : Boolean){

        val contrato = contratoRepository.findById(idContrato).orElseThrow { ContratoNotFoundException(idContrato) }

        if (contrato.dataContratacao.isAfter(LocalDate.now().minusDays(90)) or false == cobrancaEmAberto) {
            ContratoPendenciaException(contrato.titular);
        } else {
            contratoRepository.save(contrato.cancela())
        }
    }

}