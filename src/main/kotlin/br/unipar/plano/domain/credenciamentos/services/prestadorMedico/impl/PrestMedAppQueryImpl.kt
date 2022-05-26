package br.unipar.plano.domain.credenciamentos.services.prestadorMedico.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.services.prestadorMedico.PrestMedQueryService
import org.springframework.stereotype.Service


@Service
class PrestMedAppQueryImpl (private val prestadorMedicoRepository: PrestadorMedicoRepository): PrestMedQueryService {

    override fun lista(): List<PrestadorMedico> = prestadorMedicoRepository.findAll()

    override fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedico = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow{
        Exception("Prestador médico com id ${idPrestadorMedico} não encontrado")
    }
}
