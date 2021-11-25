package br.unipar.plano.domain.contratos.model


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContratoRepository : JpaRepository<Contrato, IdContrato>