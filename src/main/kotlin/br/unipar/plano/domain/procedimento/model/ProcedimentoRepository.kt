package br.unipar.plano.domain.procedimento.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProcedimentoRepository: JpaRepository<Procedimento, IdProcedimento>