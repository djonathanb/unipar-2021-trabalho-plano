package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.centrais.usecases.RegistrarCobrancaUseCase
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.service.CobrancaService
import br.unipar.plano.domain.cobrancas.usecases.CancelarCobrancaUseCase
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaDetailsDTO
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaSummaryDTO
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CobrancaServiceImpl(
    private val queryService: CobrancaQueryService,
    private val registrarCobrancaUseCase: RegistrarCobrancaUseCase,
    private val cancelarCobrancaUseCase: CancelarCobrancaUseCase,

    ) : CobrancaService {
    override fun registrarCobranca(contrato: Contrato, dataEmissao: LocalDate): IdCobranca =
        registrarCobrancaUseCase.executa(contrato, dataEmissao).id


    override fun cancelarCobranca(idCobranca: IdCobranca): CobrancaDetailsDTO =
        CobrancaDetailsDTO.toDTO(cancelarCobrancaUseCase.executa(idCobranca))


    override fun buscaTodos(): List<CobrancaSummaryDTO> = queryService.lista().map(CobrancaSummaryDTO::toDTO)

    override fun buscarPorId(id: IdCobranca): CobrancaDetailsDTO {
        return CobrancaDetailsDTO.toDTO(queryService.buscaPorId(id))

    }

    override fun buscarPorContratoAndStatus(
        contrato: Contrato,
        status: Optional<List<StatusCobranca>>
    ): List<CobrancaDetailsDTO> {
        return queryService.buscarPorContratoAndStatus(contrato, status).map(CobrancaDetailsDTO::toDTO)
    }


}
