package br.unipar.plano.domain.contratos.services



import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.interfaces.rest.contratos.ContratoDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoSummaryDTO
import java.util.*


interface ContratoApplicationService {

    fun cria(contratoDTO: ContratoDTO): IdContrato
    fun lista(): List<ContratoSummaryDTO>
    fun buscaPorId(idContrato: IdContrato): ContratoDetailsDTO
    fun deleta(idContrato: IdContrato)
    fun atualiza(idContrato: IdContrato, contratoDTO: ContratoDTO)
    fun buscaPorPlano(idPlano: Plano): List<ContratoDetailsDTO>
}