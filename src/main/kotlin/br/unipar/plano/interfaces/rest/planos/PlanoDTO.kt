package br.unipar.plano.interfaces.rest.planos

import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.model.TipoAbrangencia
import br.unipar.plano.domain.planos.model.TipoAcomodacao
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

data class PlanoSummaryDTO(
        val id: IdPlano,
        val nome: String,
        val abrangencia: TipoAbrangencia,
        val acomodacao: TipoAcomodacao,
        val obstetricia: Boolean,
        val transporteAereo: Boolean,
        val valorBase: BigDecimal,
) {

    companion object {

        fun toDTO(plano: Plano) = PlanoSummaryDTO(
                id = plano.id,
                nome = plano.nome,
                abrangencia = plano.abrangencia,
                acomodacao = plano.acomodacao,
                obstetricia = plano.obstetricia,
                transporteAereo = plano.transporteAereo,
                valorBase = plano.valorBase,
        )

    }

}

data class PlanoDetailsDTO(
        val id: IdPlano,
        val planoData: PlanoDTO
) {

    companion object {

        fun toDTO(plano: Plano) = PlanoDetailsDTO(
                id = plano.id,
                planoData = PlanoDTO.toDTO(plano)
        )

    }

}

data class PlanoDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    @field:Size(
        min = MIN_NAME_SIZE,
        max = MAX_NAME_SIZE,
        message = "O nome deve ter entre $MIN_NAME_SIZE e $MAX_NAME_SIZE caracteres"
    )
    val nome: String,

    @field:NotNull(message = "O tipo da abrangência deve ser informado")
    val abrangencia: TipoAbrangencia,

    @field:NotNull(message = "O tipo da acomodação deve ser informado")
    val acomodacao: TipoAcomodacao,

    @field:NotNull(message = "A cobertura para obstetricia deve ser informado")
    val obstetricia: Boolean,

    @field:NotNull(message = "O transporte aéreo deve ser informado")
    val transporteAereo: Boolean,

    @field:NotNull(message = "O valor base deve ser informado")
    val valorBase: BigDecimal,

) {

    fun toModel(id: IdPlano) = Plano(
        id = id,
        nome = this.nome,
        abrangencia = this.abrangencia,
        acomodacao = this.acomodacao,
        obstetricia = this.obstetricia,
        transporteAereo = this.transporteAereo,
        valorBase = this.valorBase,
    )

    companion object {

        fun toDTO(plano: Plano) = PlanoDTO(
            nome = plano.nome,
            abrangencia = plano.abrangencia,
            acomodacao = plano.acomodacao,
            obstetricia = plano.obstetricia,
            transporteAereo = plano.transporteAereo,
            valorBase = plano.valorBase,
        )
    }

}