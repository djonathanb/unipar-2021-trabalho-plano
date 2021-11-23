package br.unipar.plano.domain.carteirinha.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Carteirinha(

        @field:EmbeddedId
        val id: IdCarteirinha

);

/* Classe criada simplesmente com a finalidade de facilitar o desenvolvimento
 * Visto que a implementação da mesma depende de outra equipe */