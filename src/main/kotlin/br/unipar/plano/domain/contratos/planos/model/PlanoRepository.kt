package br.unipar.plano.domain.contratos.planos.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanoRepository : JpaRepository<Plano, IdPlano>