package br.unipar.plano.domain.cobrancas.repository

import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdContrato
import org.springframework.data.jpa.repository.JpaRepository

interface ContratoRepository : JpaRepository<Contrato, IdContrato> {
}