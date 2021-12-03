package br.unipar.plano.domain.credenciamentos.prestadorMedico.model.factories

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.Especialidade
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico

fun idPrestadorMedico(static: Boolean = true) = if (static){
    PRESTADORMEDICO_ID
}else{
    IdPrestadorMedico()
}

fun prestadorMedico(
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(),
    nome: String = PRESTADORMEDICO_NOME,
    crm: String = PRESTADORMEDICO_CRM,
    especialidade: Especialidade = especialidades(idPrestadorMedico = idPrestadorMedico)
) = PrestadorMedico(
    idPrestadorMedico = idPrestadorMedico,
    nome = nome,
    crm = crm,
    especialidades = especialidade
)

fun especialidades(
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(),
    especialidade: String = PRESTADORMEDICO_ESPECIALIDADE
) = Especialidade(
    idPrestadorMedico = idPrestadorMedico,
    especialidade = especialidade
)