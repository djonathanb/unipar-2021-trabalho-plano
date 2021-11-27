package br.unipar.plano.domain.reembolso.model

import javax.persistence.*

@Entity
class Usuario(
        @field:EmbeddedId
        val id: IdUsuario,

        @Column(nullable = false)
        val nome: String,

        @Column(nullable = false)
        val cpf: String

)