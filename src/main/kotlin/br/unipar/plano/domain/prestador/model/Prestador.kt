package br.unipar.plano.domain.prestador.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Prestador(

        @field:EmbeddedId
        val id: IdPrestador
        
);