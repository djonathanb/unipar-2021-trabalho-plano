package br.unipar.plano.domain.contratos.services.impl

import br.unipar.plano.domain.centrais.model.Contrato
import br.unipar.plano.domain.centrais.model.ContratoRepository
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.contratos.usecases.impl.ContratoNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CentralQueryServiceImpl(private val contratoRepository: ContratoRepository): ContratoQueryService {

    override fun lista(): List<Contrato> = contratoRepository.findAll()

    override fun buscaPorId(idContrato: UUID): Contrato = contratoRepository.findById(idContrato).orElseThrow{
        ContratoNotFoundException(idContrato)
    }
}