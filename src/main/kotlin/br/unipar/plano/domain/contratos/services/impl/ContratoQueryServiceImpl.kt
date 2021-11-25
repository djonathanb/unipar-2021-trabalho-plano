package br.unipar.plano.domain.contratos.services.impl


import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.contratos.usecases.impl.ContratoNotFoundException
import org.springframework.stereotype.Service


@Service
class ContratoQueryServiceImpl(private val contratoRepository: ContratoRepository): ContratoQueryService {

    override fun lista(): List<Contrato> = contratoRepository.findAll()

    override fun buscaPorId(idContrato: IdContrato): Contrato = contratoRepository.findById(idContrato).orElseThrow{
        ContratoNotFoundException(idContrato)
    }


}