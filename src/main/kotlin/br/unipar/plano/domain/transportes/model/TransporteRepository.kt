package br.unipar.plano.domain.centrais.model

import br.unipar.plano.domain.transportes.model.Transporte
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransporteRepository : JpaRepository<Transporte, IdTransporte>