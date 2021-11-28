package br.unipar.plano.domain.credenciamentos.model.prestadorMedico

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrestadorMedicoRepository : JpaRepository<PrestadorMedico, IdPrestadorMedico>