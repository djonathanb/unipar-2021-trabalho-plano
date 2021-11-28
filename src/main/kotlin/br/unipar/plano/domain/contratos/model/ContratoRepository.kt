package br.unipar.plano.domain.contratos.model


import br.unipar.plano.domain.pessoas.model.Pessoa
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContratoRepository : JpaRepository<Contrato, IdContrato> {

    fun findByPlano (idPlano: Plano): List<Contrato>

    fun findByTitularAndStatus (titular: Pessoa, statusContrato: StatusContrato): List<Contrato>
}