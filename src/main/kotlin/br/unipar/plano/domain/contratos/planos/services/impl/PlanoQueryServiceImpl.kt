package br.unipar.plano.domain.contratos.planos.services.impl


import br.unipar.plano.domain.contratos.planos.model.IdPlano
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.planos.model.PlanoRepository
import br.unipar.plano.domain.contratos.planos.services.PlanoQueryService
import br.unipar.plano.domain.contratos.planos.usecases.impl.PlanoNotFoundException
import org.springframework.stereotype.Service

@Service
class PlanoQueryServiceImpl(private val planoRepository: PlanoRepository) : PlanoQueryService {

    override fun lista(): List<Plano> = planoRepository.findAll()

    override fun buscaPorId(idPlano: IdPlano): Plano = planoRepository.findById(idPlano).orElseThrow {
        PlanoNotFoundException(idPlano)
    }



}