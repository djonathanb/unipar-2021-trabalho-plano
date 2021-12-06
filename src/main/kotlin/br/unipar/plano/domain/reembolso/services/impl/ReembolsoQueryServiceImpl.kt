package br.unipar.plano.domain.reembolso.services.impl

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.Reembolso
import br.unipar.plano.domain.reembolso.repository.ReembolsoRepository
import br.unipar.plano.domain.reembolso.services.ReembolsoQueryService
import br.unipar.plano.domain.reembolso.usecases.exceptions.ReembolsoNotFoundException
import org.springframework.stereotype.Service

@Service
class ReembolsoQueryServiceImpl(private val reembolsoRepository: ReembolsoRepository) : ReembolsoQueryService {

    override fun lista(): List<Reembolso> = reembolsoRepository.findAll()

    override fun buscaPorId(idReembolso: IdReembolso): Reembolso = reembolsoRepository.findById(idReembolso).orElseThrow {
        ReembolsoNotFoundException(idReembolso)
    }

}