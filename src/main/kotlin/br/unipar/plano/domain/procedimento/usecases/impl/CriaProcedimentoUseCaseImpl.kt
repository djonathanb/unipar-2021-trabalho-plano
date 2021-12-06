package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.model.StatusCarteirinha
import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.domain.procedimento.model.Especialidade
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.services.ProcedimentoQueryService
import br.unipar.plano.domain.procedimento.usecases.CriaProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import org.springframework.stereotype.Service

@Service
class CriaProcedimentoUseCaseImpl(private val procedimentoRepository: ProcedimentoRepository,
                                  private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService) : CriaProcedimentoUseCase {

    override fun executa(procedimento: Procedimento): Procedimento {
        if (procedimentoRepository.existsById(procedimento.id))
            throw IllegalStateException("Outro procedimento com id ${procedimento.id} já foi cadastrado!")

        if (!procedimento.carteirinha.status.equals(StatusCarteirinha.VALIDA))
            throw IllegalStateException("A carteirinha com id ${procedimento.carteirinha.numeroCarteirinha} não é válida!")

        if (!procedimento.especialidade.equals(Especialidade.CLINICA_MEDICA))
            if (solicitacaoProcedimentoQueryService.findByProcedimento_Id(procedimento.id).count() == 0)
                throw IllegalStateException("O procedimento com id ${procedimento.id} deve estar atrelado a uma liberação!")

        return procedimentoRepository.save(procedimento)
    }
}