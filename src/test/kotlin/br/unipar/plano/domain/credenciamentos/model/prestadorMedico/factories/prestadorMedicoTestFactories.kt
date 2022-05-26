package br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories

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
    especialidades: List<Especialidade> = listOf(especialidade(idPrestadorMedico = idPrestadorMedico))
) = PrestadorMedico(
    idPrestadorMedico = idPrestadorMedico,
    nome = nome,
    crm = crm,
    especialidades = especialidades
)

fun especialidade(
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(),
    especialidade: String = ""
) = Especialidade(
    id = idPrestadorMedico,
    nomeEspecialidade = especialidade
)