package br.unipar.plano.interfaces.rest.consultas

import br.unipar.plano.domain.consultas.model.TipoConsulta

import javax.validation.constraints.NotBlank

data class ConsultaDTO(

    @field:NotBlank(message = "O tipo da consulta deve ser informada")
    val tipoConsulta: TipoConsulta,

    )