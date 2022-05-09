package br.unipar.plano.infra.cobrancas.repository

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.infra.cobrancas.model.CobrancaView
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.LocalDate
import java.util.*


class CustomCobrancaQueryRepositoryImpl(private val mongoTemplate: MongoTemplate) :
    CustomCobrancaQueryRepository {
    override fun existsInMonthByContratoAndDateAndStatusNotEquals(
        idContrato: IdContrato,
        data: LocalDate,
        status: StatusCobranca
    ): Boolean {
        return mongoTemplate.exists(
            Query.query(
                Criteria.where("contrato.id").`is`(idContrato).and("dataEmissao").`is`(data).and("status").ne(status)
            ), CobrancaView::class.java
        )
    }


    override fun findAllByContratoAndStatusIn(
        idContrato: IdContrato,
        status: List<StatusCobranca>
    ): List<CobrancaView> {
        return mongoTemplate.find(
            Query.query(Criteria.where("contrato.id").`is`(idContrato).and("status").`in`(status)),
            CobrancaView::class.java
        )

    }

    override fun findAll(idContrato: IdContrato): List<CobrancaView> {
        return mongoTemplate.findAll(CobrancaView::class.java)

    }

    override fun findById(idContrato: IdContrato, idCobranca: IdCobranca): Optional<CobrancaView> {
        return Optional.ofNullable(
            mongoTemplate.find(
                Query.query(
                    Criteria.where("contrato.id").`is`(idContrato).and("id").`is`(idCobranca.id)
                ), CobrancaView::class.java
            ).first()
        )
    }
}