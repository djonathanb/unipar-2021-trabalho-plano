package br.unipar.plano.domain.reembolso.repository

import br.unipar.plano.domain.reembolso.model.RejeicaoReembolso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReembolsoRejeitadoRepository : JpaRepository<RejeicaoReembolso, UUID> {}