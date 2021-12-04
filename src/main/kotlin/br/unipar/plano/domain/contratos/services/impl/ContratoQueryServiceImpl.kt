package br.unipar.plano.domain.contratos.services.impl


import br.unipar.plano.domain.contratos.model.*
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.services.ContratoQueryService
import br.unipar.plano.domain.contratos.usecases.impl.ContratoNotFoundException
import br.unipar.plano.domain.pessoas.model.Pessoa
import org.springframework.stereotype.Service
import java.util.*


@Service
class ContratoQueryServiceImpl(private val contratoRepository: ContratoRepository): ContratoQueryService {

    override fun lista(): List<Contrato> = contratoRepository.findAll()

    override fun buscaPorId(idContrato: IdContrato): Contrato = contratoRepository.findById(idContrato).orElseThrow{ ContratoNotFoundException(idContrato) }

    override fun buscaPorPlano(idPlano: Plano): List<Contrato> {
        return contratoRepository.findByPlano(idPlano);
    }

    override fun findByTitularAndStatus(titular: Pessoa, statusContrato: StatusContrato): List<Contrato> {
        return contratoRepository.findByTitularAndStatus(titular, statusContrato)
    }

    override fun buscaPorPlanoeStatus(
        idPlano: Plano,
        status: Optional<List<StatusContrato>>
    ): List<Contrato> {
        return contratoRepository.findbyPlanoeStatus(idPlano, status.orElse(null))
    }

}