package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.domain.centrais.services.CentralQueryService
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.CentralSummaryDTO
import br.unipar.plano.interfaces.rest.centrais.EnderecoDTO
import org.springframework.stereotype.Service

@Service
class CentralApplicationServiceImpl(
    private val centralQueryService: CentralQueryService,
    private val criaCentralUseCase: CriaCentralUseCase
) : CentralApplicationService {

    override fun cria(centralDTO: CentralDTO): IdCentral {
        val central = toModel(centralDTO)
        val novaCentral = criaCentralUseCase.executa(central)
        return novaCentral.id
    }

    override fun lista(): List<CentralSummaryDTO> = centralQueryService.lista().map {
        toSummaryDTO(it)
    }

    override fun buscaPorId(idCentral: IdCentral): CentralDetailsDTO = centralQueryService.buscaPorId(idCentral).let {
        toDetailsDTO(it)
    }

    private fun toModel(centralDTO: CentralDTO) = Central(
        id = IdCentral(),
        nome = centralDTO.nome,
        cnpj = centralDTO.cnpj,
        endereco = Endereco(
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