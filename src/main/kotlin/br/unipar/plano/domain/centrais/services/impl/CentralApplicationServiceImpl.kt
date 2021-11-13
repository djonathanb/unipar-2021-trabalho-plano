package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import org.springframework.stereotype.Service

@Service
class CentralApplicationServiceImpl(private val criaCentralUseCase: CriaCentralUseCase) : CentralApplicationService {

    override fun criar(centralDTO: CentralDTO): IdCentral {
        val central = Central(
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
        val novaCentral = criaCentralUseCase.executa(central)
        return novaCentral.id
    }

}