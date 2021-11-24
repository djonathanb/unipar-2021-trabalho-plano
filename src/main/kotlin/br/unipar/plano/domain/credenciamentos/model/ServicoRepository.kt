package br.unipar.plano.domain.credenciamentos.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicoRepository : JpaRepository<Servico, IdServico>