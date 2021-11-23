package br.unipar.plano.domain.contrato.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Contrato(

        @field:EmbeddedId
        val id: IdContrato
        
);