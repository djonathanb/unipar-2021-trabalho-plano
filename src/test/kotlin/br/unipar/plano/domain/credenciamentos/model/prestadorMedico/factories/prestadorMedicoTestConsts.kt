package br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.StatusMedico
import java.util.*

val PRESTADORMEDICO_ID = IdPrestadorMedico(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val PRESTADOR_STATUS = StatusMedico.CRIADO
const val ID_ESPECIALIDADE = 12353464
const val PRESTADORMEDICO_NOME = "João da Silva"
const val PRESTADORMEDICO_CRM = "112345/SP"
const val PRESTADORMEDICO_ESPECIALIDADE_ID = ID_ESPECIALIDADE