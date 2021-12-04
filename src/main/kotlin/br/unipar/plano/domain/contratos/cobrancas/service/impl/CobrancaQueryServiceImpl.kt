package br.unipar.plano.domain.contratos.cobrancas.service.impl

import br.unipar.plano.domain.contratos.cobrancas.model.Cobranca
import br.unipar.plano.domain.contratos.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.domain.contratos.model.Contrato
import org.springframework.stereotype.Service
import java.util.*

@Service
class CobrancaQueryServiceImpl() : CobrancaQueryService {

    override fun buscarPorContratoAndStatus(
        contrato: Contrato,
        status: Optional<List<StatusCobranca>>
    ): List<Cobranca> {
        return emptyList()
    }

}