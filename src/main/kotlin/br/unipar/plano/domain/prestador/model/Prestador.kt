package br.unipar.plano.domain.prestador.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Prestador(

        @field:EmbeddedId
        val id: IdPrestador
        
);

/* Classe criada simplesmente com a finalidade de facilitar o desenvolvimento
 * Visto que a implementação da mesma depende de outra equipe */