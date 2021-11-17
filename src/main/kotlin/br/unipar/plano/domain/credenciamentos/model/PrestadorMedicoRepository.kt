package br.unipar.plano.domain.credenciamentos.model

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.IdCentral
import org.springframework.data.jpa.repository.JpaRepository

interface PrestadorMedicoRepository : JpaRepository<PrestadorMedico, IdPrestadorMedico>