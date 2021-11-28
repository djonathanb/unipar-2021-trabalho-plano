package br.unipar.plano.domain.pessoas.model


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<Pessoa, IdDependente>