package br.unipar.plano.domain.credenciamentos.services.impl

import br.unipar.plano.domain.credenciamentos.model.*
import br.unipar.plano.domain.credenciamentos.services.EspecialidadeQueryService
import br.unipar.plano.domain.credenciamentos.services.PrestMedQueryService
import org.springframework.stereotype.Service

class EspecialidadeAppServiceImpl {

    @Service
    class PrestMedAppQueryImpl (private val especialidadeRepository: EspecialidadeRepository): EspecialidadeQueryService{

        override fun lista(): List<Especialidade> = especialidadeRepository.findAll()

        override fun buscaPorId(idEspecialidade: IdEspecialidade): Especialidade = especialidadeRepository.findById(idEspecialidade).orElseThrow{
            Exception("Prestador médico com id ${idEspecialidade} não encontrado")
        }
    }

}