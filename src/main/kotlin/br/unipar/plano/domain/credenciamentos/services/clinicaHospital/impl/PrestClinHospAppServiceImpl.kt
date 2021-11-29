package br.unipar.plano.domain.credenciamentos.services.clinicaHospital.impl

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.Servico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.clinicaHospital.PrestClinHospAppService
import br.unipar.plano.domain.credenciamentos.services.clinicaHospital.PrestClinHospQueryService
import br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.CriaPrestClinHospUseCase
import br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital.*
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestMedDTO
import org.springframework.stereotype.Service



@Service
class PrestClinHospAppServiceImpl(
    private val prestClinHospQueryService: PrestClinHospQueryService,
    private val criaPrestClinHospUseCase: CriaPrestClinHospUseCase
) : PrestClinHospAppService {

    override fun cria(prestClinHospDTO: PrestClinHospDTO): IdPrestadorClinicaHospital {
        val clinicaHospital = prestClinHospDTO.toModel(IdPrestadorClinicaHospital(), prestClinHospDTO)
        val novoClinHosp = criaPrestClinHospUseCase.executa(clinicaHospital)
        return novoClinHosp.id
    }

    override fun lista(): List<PrestClinHospSummaryDTO> = prestClinHospQueryService.lista().map(PrestClinHospSummaryDTO::toDTO)

    override fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestClinHospDetailsDTO {
        val prestadorClinicaHospital = prestClinHospQueryService.buscaPorId(idPrestadorClinicaHospital)
        return PrestClinHospDetailsDTO.toDTO(prestadorClinicaHospital)
    }

    override fun atualiza(idPrestadorClinicaHospital: IdPrestadorClinicaHospital, prestClinHospDTO: PrestClinHospDTO) {
        atualizaClinHospUseCase.executa(idPrestadorClinicaHospital) {
            it.with(
                nome = .nome
            )
        }
    }

    override fun deleta(idPrestadorClinicaHospital: IdPrestadorClinicaHospital) {
        deletaPrestClinHospUseCase.executa(idPrestadorClinicaHospital)
    }

    override fun credenciar(idPrestadorClinicaHospital: IdPrestadorClinicaHospital) {
        credenciaPrestClinHospUseCase.executa(idPrestadorClinicaHospital)
    }

    override fun descredenciar(idPrestadorMedico: IdPrestadorMedico) {
        descredenciaPresClinHospUseCase.executa(idPrestadorMedico)
    }

}
