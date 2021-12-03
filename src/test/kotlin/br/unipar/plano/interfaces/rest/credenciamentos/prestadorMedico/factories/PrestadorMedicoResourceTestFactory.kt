package br.unipar.plano.interfaces.rest.centrais.factories

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoDetailsDTO

fun PrestMedDTO(
    nome: String = PRESTADORMEDICO_CO_NOME,
    crm: String = PRESTADORMEDICO_CO_CRM,
    especialidade: String = PRESTADORMEDICO_CO_ESPECIALIDADE
) = PrestMedDTO(
    nome = nome,
    crm = crm,
    especialidade = especialidade
)

fun EspecialidadeDTO(
    especialidade: String
) = EspecialidadeDTO(
    especialidade = especialidade
)

fun PrestadorMedicoSummaryDTO(
    staticId: Boolean = true,
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(staticId),
    nome: String = PRESTADORMEDICO_CO_NOME,
    crm: String = PRESTADORMEDICO_CO_CRM
) = PrestadorMedicoSummaryDTO(
    id = idPrestadorMedico,
    nome = nome,
    crm = crm
)

val id: IdPrestadorMedico,
val nome: String,
val status: String

fun prestadorMedicoDetailsDTO(
    staticId: Boolean = true,
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(staticId),
    status: StatusCentral = PRESTADORMEDICO_CO_STATUS,
    prestMedData: PrestadorMedicoDetailsDTO = PrestMedDTO()
) = CentralDetailsDTO(
    id = idPrestadorMedico,
    status = status,
    prestMedData = prestMedData
)