package br.unipar.plano.infra.cobrancas.repository

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface CobrancaRepository : JpaRepository<Cobranca, IdCobranca> {

    @Deprecated(
        message = "Procurar usar a função findAll(idContrato) que passa o contrato como filtro",
        replaceWith = ReplaceWith("this.findAll(idContrato)")
    )
    override fun findAll(): MutableList<Cobranca>

    @Deprecated(
        message = "Procurar usar a função findById(idContrato, idCobranca) que passa o contrato como filtro",
        replaceWith = ReplaceWith("this.findById(idContrato, idCobranca)")
    )
    override fun findById(id: IdCobranca): Optional<Cobranca>
}