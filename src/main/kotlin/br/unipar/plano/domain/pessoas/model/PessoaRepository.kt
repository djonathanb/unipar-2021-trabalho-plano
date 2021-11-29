package br.unipar.plano.domain.pessoas.model


import br.unipar.plano.domain.dependentes.model.IdDependente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<Pessoa, IdDependente>