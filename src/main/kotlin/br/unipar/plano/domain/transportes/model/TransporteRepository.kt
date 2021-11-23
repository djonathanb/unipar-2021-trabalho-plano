package br.unipar.plano.domain.centrais.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransporteRepository : JpaRepository<Transporte, IdTransporte>