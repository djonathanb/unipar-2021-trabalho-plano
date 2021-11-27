package br.unipar.plano.domain.credenciamentos.services.impl

import br.unipar.plano.domain.credenciamentos.model.*
import br.unipar.plano.domain.credenciamentos.services.PrestClinHospAppService
import br.unipar.plano.domain.credenciamentos.services.PrestClinHospQueryService
import br.unipar.plano.domain.credenciamentos.usecases.CriaPrestClinHospUseCase
import br.unipar.plano.interfaces.rest.credenciamentos.*
import org.springframework.stereotype.Service



@Service
class PrestClinHospAppServiceImpl(
    private val prestClinHospQueryService: PrestClinHospQueryService,
    private val criaPrestClinHospUseCase: CriaPrestClinHospUseCase
) : PrestClinHospAppService {

    override fun cria(prestClinHospDTO: PrestClinHospDTO): IdPrestadorClinicaHospital {
        val clinicaHospital = toModel(IdPrestadorClinicaHospital(), prestClinHospDTO)
        val novoClinHosp = criaPrestClinHospUseCase.executa(clinicaHospital)
        return novoClinHosp.id
    }

    override fun lista(): List<PrestClinHospSummaryDTO> = prestClinHospQueryService.lista().map {
        toSummaryDTO(it)
    }

    override fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestClinHospDetailsDTO =
        toDetailsDTO(prestClinHospQueryService.buscaPorId(IdPrestadorClinicaHospital()))

    private fun toModel(id: IdPrestadorClinicaHospital, prestClinHospDTO: PrestClinHospDTO) = PrestadorClinicaHospital(
        id = id,
        nome = prestClinHospDTO.nome,
        cnpj = prestClinHospDTO.cnpj,
        status = prestClinHospDTO.status,
        servico = prestClinHospDTO.servicos.map { servico -> Servico(id = IdPrestadorClinicaHospital(), servico = servico.servico)}
    )

    private fun toSummaryDTO(prestadorClinicaHospital: PrestadorClinicaHospital) = PrestClinHospSummaryDTO(
        id = prestadorClinicaHospital.id,
        nome = prestadorClinicaHospital.nome,
        cnpj = prestadorClinicaHospital.cnpj,
        status = prestadorClinicaHospital.status,
        servicos =  prestadorClinicaHospital.servico.map{ servico -> ServicoSummaryDTO(servico = servico.servico) }
    )

    private fun toDetailsDTO(prestadorClinicaHospital: PrestadorClinicaHospital) = PrestClinHospDetailsDTO(
        id = prestadorClinicaHospital.id,
        prestClinHospData = toDTO(prestadorClinicaHospital)
    )

    private fun toDTO(prestadorClinicaHospital: PrestadorClinicaHospital) = PrestClinHospDTO(
        nome = prestadorClinicaHospital.nome,
        cnpj = prestadorClinicaHospital.cnpj,
        status = prestadorClinicaHospital.status,
        servicos = prestadorClinicaHospital.servico.map{ servico -> ServicoDTO(servico = servico.servico) }
    )


}
