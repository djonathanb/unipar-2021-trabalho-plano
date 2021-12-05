package br.unipar.plano.domain.prenatais.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PreNatalRepository : JpaRepository<PreNatal, IdPreNatal>