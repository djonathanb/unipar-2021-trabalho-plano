package br.unipar.plano.infra.cobrancas.repository

import br.unipar.plano.infra.cobrancas.model.CobrancaView
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CobrancaQueryRepository : MongoRepository<CobrancaView, UUID>, CustomCobrancaQueryRepository