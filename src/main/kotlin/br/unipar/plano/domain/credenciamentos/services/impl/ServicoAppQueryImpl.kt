package br.unipar.plano.domain.credenciamentos.services.impl


import br.unipar.plano.domain.credenciamentos.model.IdServico
import br.unipar.plano.domain.credenciamentos.model.Servico
import br.unipar.plano.domain.credenciamentos.model.ServicoRepository
import br.unipar.plano.domain.credenciamentos.services.ServicoQueryService
import org.springframework.stereotype.Service

@Service
class ServicoAppQueryImpl (private val servicoRepository: ServicoRepository): ServicoQueryService {

    override fun lista(): List<Servico> = servicoRepository.findAll()

    override fun buscaPorId(idServico: IdServico): Servico = servicoRepository.findById(idServico).orElseThrow{
        Exception("Prestador médico com id ${idServico} não encontrado")
    }
}
