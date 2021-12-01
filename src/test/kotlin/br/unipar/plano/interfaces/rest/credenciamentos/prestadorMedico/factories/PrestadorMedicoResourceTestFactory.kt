package br.unipar.plano.interfaces.rest.centrais.factories

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.CentralSummaryDTO
import br.unipar.plano.interfaces.rest.centrais.EnderecoDTO

fun PrestMedDTO(
    nome: String = PRESTADORMEDICO_CO_NOME,
    crm: String = PRESTADOR_CO_CRM,
    status: String = PRESTADOR_CO_STRING,
    especialidade: String = PRESTADOR_CO_STRING
) = PrestMedDTO(
    nome = nome,
    crm = crm,
    status = status,
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
    nome: String = CENTRAL_CO_NOME,
    crm: Int = CENTRAL_CO_CRM
) = PrestadorMedicoSummaryDTO(
    id = idCentral,
    nome = nome,
    cidade = cidade
)


val id: IdPrestadorMedico,
val nome: String,
val crm: String


fun centralDetailsDTO(
    staticId: Boolean = true,
    idCentral: IdCentral = idCentral(staticId),
    status: StatusCentral = CENTRAL_CO_STATUS,
    centralData: CentralDTO = centralDTO()
) = CentralDetailsDTO(
    id = idCentral,
    status = status,
    centralData = centralData
)
