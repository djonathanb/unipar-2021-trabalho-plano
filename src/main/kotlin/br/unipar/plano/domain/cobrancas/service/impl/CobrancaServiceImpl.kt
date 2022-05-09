package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.centrais.usecases.RegistrarCobrancaUseCase
import br.unipar.plano.domain.cobrancas.commands.CancelarCobrancaCommand
import br.unipar.plano.domain.cobrancas.commands.RegistrarCobrancaCommand
import br.unipar.plano.domain.cobrancas.model.Cirurgia
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.model.Procedimento
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
    override fun registrarCobranca(
        idContrato: IdContrato, dataEmissao: LocalDate, cirurgias: Collection<Cirurgia>,
        procedimentos: Collection<Procedimento>
    ): IdCobranca =
        registrarCobrancaUseCase.executa(RegistrarCobrancaCommand(idContrato, dataEmissao, cirurgias, procedimentos)).id


    override fun cancelarCobranca(idContrato: IdContrato, idCobranca: IdCobranca): CobrancaDetailsDTO =
        CobrancaDetailsDTO.toDTO(cancelarCobrancaUseCase.executa(CancelarCobrancaCommand(idContrato, idCobranca)))


    override fun buscaTodos(idContrato: IdContrato): List<CobrancaSummaryDTO> =
        queryService.lista(idContrato).map(CobrancaSummaryDTO::toDTO)

    override fun buscarPorId(idContrato: IdContrato, id: IdCobranca): CobrancaDetailsDTO {
        return CobrancaDetailsDTO.toDTO(queryService.buscaPorId(idContrato, id))

    }

    override fun buscarPorContratoAndStatus(
        idContrato: IdContrato,
        status: Optional<List<StatusCobranca>>
    ): List<CobrancaDetailsDTO> {
        return queryService.buscarPorContratoAndStatus(idContrato, status).map(CobrancaDetailsDTO::toDTO)
    }


}
