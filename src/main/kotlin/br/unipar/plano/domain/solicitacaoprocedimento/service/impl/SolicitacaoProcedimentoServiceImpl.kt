package br.unipar.plano.domain.solicitacaoprocedimento.service.impl;

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoService
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoSummaryDTO
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
public class SolicitacaoProcedimentoServiceImpl(
    private val solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository
) : SolicitacaoProcedimentoService {

    override fun insert(dto: SolicitacaoProcedimentoDTO): IdSolicitacaoProcedimento {
        validate(dto);
        val solicitacaoProcedimento = dto.toModel(IdSolicitacaoProcedimento());
        val novaSolicitacao = solicitacaoProcedimentoRepository.save(solicitacaoProcedimento);
        return novaSolicitacao.id
    }

    override fun lista(): List<SolicitacaoProcedimentoSummaryDTO> = solicitacaoProcedimentoRepository
        .findAll().map(SolicitacaoProcedimentoSummaryDTO::toDTO);


    override fun buscaPorId(idSolicitacaoProcedimento: IdSolicitacaoProcedimento): SolicitacaoProcedimentoDetailsDTO {
        val solicitacaoProcedimento =
            solicitacaoProcedimentoRepository.findById(idSolicitacaoProcedimento).orElseThrow {
                NotFoundException("Solicitacao com id ${idSolicitacaoProcedimento.id} não encontrada")
            };

        return SolicitacaoProcedimentoDetailsDTO.toDTO(solicitacaoProcedimento);
    }

    override fun deleta(idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        solicitacaoProcedimentoRepository.delete(idSolicitacaoProcedimento);
    }

    override fun liberarSolicitacao(solicitacaoID: IdSolicitacaoProcedimento) {
        //validar carteirinha //TODO
        val solicitacaoProcedimento = solicitacaoProcedimentoRepository.findById(solicitacaoID);

        if (solicitacaoProcedimento.isPresent
            && !solicitacaoProcedimento.get().statusSolicitacao.equals(StatusSolicitacaoProcedimento.LIBERADO)
        ) {
            val solicitacoes = solicitacaoProcedimentoRepository.findByProcedimentoAndStatusSolicitacaoEquals(
                solicitacaoProcedimento.get().procedimento,
                StatusSolicitacaoProcedimento.LIBERADO
            );

            if (!existeDuasSolitacoesLiberadasNoMesmoMes(solicitacoes, solicitacaoProcedimento.get())) {
                solicitacaoProcedimento.get().statusSolicitacao = StatusSolicitacaoProcedimento.LIBERADO;
                solicitacaoProcedimento.get().dataLiberacaoRejeicao = LocalDate.now();
                solicitacaoProcedimentoRepository.save(solicitacaoProcedimento);
            }
        }
    }

    override fun rejeitarSolicitacao(solicitacaoID: IdSolicitacaoProcedimento, descricaoRejeicao: String) {
        val solicitacaoProcedimento = solicitacaoProcedimentoRepository.findById(solicitacaoID);

        if (solicitacaoProcedimento.isPresent && solicitacaoProcedimento.get().statusSolicitacao.equals(
                StatusSolicitacaoProcedimento.ABERTO
            )
        ) {
            solicitacaoProcedimento.get().statusSolicitacao = StatusSolicitacaoProcedimento.REJEITADO;
            solicitacaoProcedimento.get().descricaoRejeicao = descricaoRejeicao;
            solicitacaoProcedimento.get().dataLiberacaoRejeicao = LocalDate.now();
            solicitacaoProcedimentoRepository.save(solicitacaoProcedimento);
        }
    }

    private fun validate(solicitacaoProcedimentoDTO: SolicitacaoProcedimentoDTO) {
//      validar carteirinha
        ValidadorSolicitacaoProcedimento.validarObrigatoridadePrestadorMedico(solicitacaoProcedimentoDTO);
        ValidadorSolicitacaoProcedimento.validarProcedimentoRestrito(
            solicitacaoProcedimentoDTO,
            solicitacaoProcedimentoRepository
        );
    }

    private fun existeDuasSolitacoesLiberadasNoMesmoMes(
        solicitacoesProcecimentos: List<SolicitacaoProcedimento>,
        solicitacaoProcedimento: SolicitacaoProcedimento
    ): Boolean {

        val solicitacaoProcedimentoMesmoMes =
            solicitacoesProcecimentos.filter { solicitacao -> solicitacao.dataLiberacaoRejeicao.dayOfMonth == solicitacaoProcedimento.dataLiberacaoRejeicao.dayOfMonth };

        return solicitacaoProcedimentoMesmoMes.size > 1;
    }
}