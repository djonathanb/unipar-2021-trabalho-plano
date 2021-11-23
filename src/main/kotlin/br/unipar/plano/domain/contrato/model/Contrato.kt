package br.unipar.plano.domain.contrato.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Contrato(

        @field:EmbeddedId
        val id: IdContrato
        
);

/* Classe criada simplesmente com a finalidade de facilitar o desenvolvimento
 * Visto que a implementação da mesma depende de outra equipe */