package br.unipar.plano.domain.reembolso.usecases.impl

import br.unipar.plano.domain.reembolso.model.*
import br.unipar.plano.domain.reembolso.repository.CarteirinhaRepository
import br.unipar.plano.domain.reembolso.repository.PlanoRepository
import br.unipar.plano.domain.reembolso.repository.ReembolsoRepository
import br.unipar.plano.domain.reembolso.usecases.SolicitaReembolsoUseCase
import br.unipar.plano.domain.reembolso.usecases.exceptions.AreaAbrangenciaInvalidaException
import br.unipar.plano.domain.reembolso.usecases.exceptions.CarteirinhaInvalidaException
import br.unipar.plano.domain.reembolso.usecases.exceptions.SolicitacaoException
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class SolicitaReembolsoUseCaseImpl(
        private val reembolsoRepository: ReembolsoRepository,
        private val carteirinhaRepository: CarteirinhaRepository,
        private val planoRepository: PlanoRepository
) : SolicitaReembolsoUseCase {

    override fun executa(reembolso: Reembolso): Reembolso {
        val idUsuario = reembolso.usuario.id
        val carteirinha = carteirinhaRepository.findByCarteirinha(idUsuario.id)
        val plano =  planoRepository.findByPlano(idUsuario.id)

        if(carteirinha.statusCarteirinha != StatusCarteirinha.ATIVA) {
            throw CarteirinhaInvalidaException("A solicitação deve ser solicitada para uma carteirinha válida.")
        }

        if (reembolso.estadoSolicitacao == plano.areaAbrangencia) {
            throw AreaAbrangenciaInvalidaException("A solicitação deve ser feita para um estado fora da Área de Abrangência do plano")
        }

        val dataSolicitacao = LocalDate.of(2021, 1, 1)
        val totalSolicitacoesRealizadas = reembolsoRepository.totalSolicitacoesAno(idUsuario, dataSolicitacao)

        val valorReembolso = reembolso.valor
        val valorTotalSolicitacoesRealizadas = reembolsoRepository.valorTotalSolicitacoesAno(idUsuario, dataSolicitacao) + valorReembolso

        if(totalSolicitacoesRealizadas > 4 && valorTotalSolicitacoesRealizadas
                        .compareTo(BigDecimal("5000")) > 0 ) {
            throw SolicitacaoException("É permitido no máximo quatro solicitações de reembolso ano no valor total de R\$5000,00.")
        }

        return reembolsoRepository.save(reembolso)
    }

}