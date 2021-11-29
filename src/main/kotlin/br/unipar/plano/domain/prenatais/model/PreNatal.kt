package br.unipar.plano.domain.prenatais.model

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.consultas.model.Consulta
import br.unipar.plano.domain.prenatais.ErroSolicitaoConsultaException
import br.unipar.plano.interfaces.rest.FalhaAtendimentoException
import java.time.LocalDate
import javax.persistence.*
import kotlin.collections.ArrayList

enum class StatusAtendimento {
    SOLICITADO, AUTORIZADO, CANCELADO;
}

@Entity
class PreNatal(
    @field:EmbeddedId
    val id: IdPreNatal,

    @OneToOne(cascade = [CascadeType.ALL])
    val carteirinha: Carteirinha,

    @Temporal(TemporalType.TIMESTAMP)
    val dataSolicitacao: LocalDate = LocalDate.now(),

    @Column
    val temObstetricia: Boolean = false,

    @OneToMany(cascade = [CascadeType.ALL])
    val consultas: List<Consulta>,

    @Enumerated(EnumType.STRING)
    val status: StatusAtendimento = StatusAtendimento.SOLICITADO,

    ) {

    fun solicitar(): PreNatal {
        if (!carteirinha.valida) {
            throw ErroSolicitaoConsultaException("Não é possível solicitar Pré-Natal, carterinha inválida")
        } else if (!temObstetricia) {
            throw ErroSolicitaoConsultaException("Não é possível solicitar Pré-Natal, não tem obstetrícia")
        } else if (!LocalDate.now().plusYears(1).isAfter(dataSolicitacao)) {
            throw ErroSolicitaoConsultaException("Não é possível solicitar Pré-Natal, você só pode ter um Pré-Natal por ano")
        }
        return copy(status = StatusAtendimento.SOLICITADO)
    }

    fun cancelar(): PreNatal {
        if (status != StatusAtendimento.SOLICITADO) {
            throw FalhaAtendimentoException("Não é possível cancelar um Pre-Natal com status $status")
        }
        return copy(status = StatusAtendimento.CANCELADO)
    }

    fun autorizar(): PreNatal {
        if (status != StatusAtendimento.SOLICITADO || status == StatusAtendimento.AUTORIZADO || status == StatusAtendimento.CANCELADO) {
            throw FalhaAtendimentoException("Não é possível cancelar um Pre-Natal com status $status")
        }
        val consultasGratis: MutableList<Consulta> = ArrayList()

        // Fazer parte de dar 9 consultas e 1 cirurgia


        return copy(status = StatusAtendimento.AUTORIZADO, consultas = consultasGratis)
    }


    fun with(
        id: IdPreNatal = this.id,
        carteirinha: Carteirinha = this.carteirinha,
        dataSolicitacao: LocalDate = this.dataSolicitacao,
        temObstetricia: Boolean = this.temObstetricia,
        consultas: List<Consulta> = this.consultas,
        status: StatusAtendimento = this.status,
    ) = copy(
        id = id,
        carteirinha = carteirinha,
        dataSolicitacao = dataSolicitacao,
        temObstetricia = temObstetricia,
        consultas = consultas,
        status = status,
    )

    private fun copy(
        id: IdPreNatal = this.id,
        carteirinha: Carteirinha = this.carteirinha,
        dataSolicitacao: LocalDate = this.dataSolicitacao,
        temObstetricia: Boolean = this.temObstetricia,
        consultas: List<Consulta> = this.consultas,
        status: StatusAtendimento = this.status,
    ) = PreNatal(
        id = id,
        carteirinha = carteirinha,
        dataSolicitacao = dataSolicitacao,
        temObstetricia = temObstetricia,
        consultas = consultas,
        status = status,
    )
}