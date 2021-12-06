package br.unipar.plano.domain.carteirinha.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Carteirinha(

        @field:EmbeddedId
        val id: IdCarteirinha,

        @Enumerated(EnumType.STRING)
        val status: StatusCarteirinha

);

/* Classe criada simplesmente com a finalidade de facilitar o desenvolvimento
 * Visto que a implementação da mesma depende de outra equipe
 * */