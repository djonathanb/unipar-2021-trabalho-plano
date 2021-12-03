package br.unipar.plano.domain.contratos.cobrancas.service.impl


import br.unipar.plano.domain.contratos.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.contratos.cobrancas.service.CobrancaService
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.interfaces.rest.contratos.cobrancas.CobrancaDetailsDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class CobrancaServiceImpl(
    private val queryService: CobrancaQueryService,


    ) : CobrancaService {

    override fun buscarPorContratoAndStatus(
        contrato: Contrato,
        status: Optional<List<StatusCobranca>>
        ): List<CobrancaDetailsDTO> {
        return queryService.buscarPorContratoAndStatus(contrato, status).map(CobrancaDetailsDTO::toDTO)
    }


}
