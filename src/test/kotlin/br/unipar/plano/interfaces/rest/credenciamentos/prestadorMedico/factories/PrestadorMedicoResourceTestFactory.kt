package br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.factories

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.StatusMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.*
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.*

fun prestMedDTO(
    nome: String = PRESTADORMEDICO_NOME,
    crm: String = PRESTADORMEDICO_CRM,
    especialidades: List<EspecialidadeDTO> = listOf()
) = PrestMedDTO(
    nome = nome,
    crm = crm,
    especialidades = especialidades
)

fun especialidadeDTO(
    especialidade: String
) = EspecialidadeDTO(
    nomeEspecialidade = especialidade
)

fun prestadorMedicoSummaryDTO(
    staticId: Boolean = true,
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(staticId),
    nome: String = PRESTADORMEDICO_NOME,
    crm: String = PRESTADORMEDICO_CRM,
    especialidades: List<EspecialidadeSummaryDTO> = listOf()
) = PrestadorMedicoSummaryDTO(
    id = idPrestadorMedico,
    nome = nome,
    crm = crm,
    especialidades = especialidades
)

fun prestadorMedicoDetailsDTO(
    staticId: Boolean = true,
    idPrestadorMedico: IdPrestadorMedico = idPrestadorMedico(staticId),
    status: StatusMedico = PRESTADOR_STATUS,
    prestMedData: PrestMedDTO = prestMedDTO()
) = PrestadorMedicoDetailsDTO(
    id = idPrestadorMedico,
    status = status,
    prestMedData = prestMedData
)
