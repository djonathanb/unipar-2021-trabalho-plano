package br.unipar.plano.domain.credenciamentos.services.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoSummaryDTO

interface PrestMedAppService {
    fun cria(prestMedDTO: PrestMedDTO): IdPrestadorMedico
    fun atualiza(idPrestadorMedico: IdPrestadorMedico, prestadorMedicoDTO: PrestMedDTO)
    fun deleta(idPrestadorMedico: IdPrestadorMedico)
    fun lista(): List<PrestadorMedicoSummaryDTO>
    fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedicoDetailsDTO
    fun credenciar(idPrestadorMedico: IdPrestadorMedico)
    fun descredenciar(idPrestadorMedico: IdPrestadorMedico)
}

