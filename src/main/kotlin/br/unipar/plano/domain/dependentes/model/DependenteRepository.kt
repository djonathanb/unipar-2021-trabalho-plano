package br.unipar.plano.domain.dependentes.model


import br.unipar.plano.domain.contratos.model.Contrato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DependenteRepository : JpaRepository<Dependente, IdDependente>{

    fun findByContrato(contrato: Contrato): List<Dependente>

}