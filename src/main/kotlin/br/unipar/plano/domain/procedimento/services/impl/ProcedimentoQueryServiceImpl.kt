package br.unipar.plano.domain.procedimento.services.impl

import br.unipar.plano.domain.prestador.model.IdPrestador
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.services.ProcedimentoQueryService
import br.unipar.plano.domain.procedimento.services.ProcedimentoService
import br.unipar.plano.domain.procedimento.usecases.CriaProcedimentoUseCase
import br.unipar.plano.domain.procedimento.usecases.CancelaProcedimentoUseCase
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoSummaryDTO
import org.springframework.stereotype.Service

@Service
class ProcedimentoQueryServiceImpl(
        private val procedimentoService: ProcedimentoService,
        private val criaProcedimentoUseCase: CriaProcedimentoUseCase,
        private val cancelaProcedimentoUseCase: CancelaProcedimentoUseCase
) : ProcedimentoQueryService {

    override fun cria(procedimentoDTO: ProcedimentoDTO): IdProcedimento {
        val procedimento = procedimentoDTO.toModel(IdProcedimento())
        val novoProcedimento = criaProcedimentoUseCase.executa(procedimento)
        return novoProcedimento.id
    }

    override fun cancela(idProcedimento: IdProcedimento) {
        cancelaProcedimentoUseCase.executa(idProcedimento)
    }

    override fun lista(idPrestador: IdPrestador, year: Int, month: Int) =
            procedimentoService.lista(idPrestador, year, month).map(ProcedimentoSummaryDTO::toDTO)

    override fun buscaPorId(idProcedimento: IdProcedimento): ProcedimentoDetailsDTO {
        val procedimento = procedimentoService.buscaPorId(idProcedimento)
        return ProcedimentoDetailsDTO.toDTO(procedimento)
    }
}