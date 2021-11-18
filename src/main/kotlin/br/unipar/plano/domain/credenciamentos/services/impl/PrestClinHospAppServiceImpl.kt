package br.unipar.plano.domain.credenciamentos.services.impl

import br.unipar.plano.domain.centrais.services.CentralQueryService
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.services.PrestClinHospQueryService
import br.unipar.plano.domain.credenciamentos.usecases.CriaPrestClinHospUseCase
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.CentralSummaryDTO
import br.unipar.plano.interfaces.rest.centrais.EnderecoDTO
import org.springframework.stereotype.Service

@Service
class PrestClinHospAppServiceImpl {
    private val prestClinHospQueryService: PrestClinHospQueryService,
    private val criaPrestClinHospUseCase: CriaPrestClinHospUseCase
    ) : PrestClinHospAppService {

        override fun cria(centralDTO: CentralDTO): IdPrestadorClinicaHospital {
            val central = toModel(IdCentral(), centralDTO)
            val novaCentral = criaCentralUseCase.executa(central)
            return novaCentral.id
        }

        override fun lista(): List<CentralSummaryDTO> = centralQueryService.lista().map {
            toSummaryDTO(it)
        }

        override fun buscaPorId(idCentral: IdCentral): CentralDetailsDTO = toDetailsDTO(centralQueryService.buscaPorId(idCentral))

        private fun toModel(id: IdCentral, centralDTO: CentralDTO) = Central(
            id = id,
            nome = centralDTO.nome,
            cnpj = centralDTO.cnpj,
            endereco = Endereco(
                idCentral = id,
                cidade = centralDTO.endereco.cidade,
                cep = centralDTO.endereco.cep,
                bairro = centralDTO.endereco.bairro,
                logradouro = centralDTO.endereco.logradouro,
                numero = centralDTO.endereco.numero,
                complemento = centralDTO.endereco.complemento
            )
        )

        private fun toDetailsDTO(central: Central) = CentralDetailsDTO(
            id = central.id,
            centralData = toDTO(central)
        )

        private fun toDTO(central: Central) = CentralDTO(
            nome = central.nome,
            cnpj = central.cnpj,
            endereco = EnderecoDTO(
                cidade = central.endereco.cidade,
                cep = central.endereco.cep,
                bairro = central.endereco.bairro,
                logradouro = central.endereco.logradouro,
                numero = central.endereco.numero,
                complemento = central.endereco.complemento
            )
        )

        private fun toSummaryDTO(central: Central) = CentralSummaryDTO(
            id = central.id,
            nome = central.nome,
            cidade = central.endereco.cidade,
        )

    }