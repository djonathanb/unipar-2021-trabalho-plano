package br.unipar.plano.interfaces.dto

import br.unipar.plano.domain.reembolso.model.IdUsuario
import br.unipar.plano.domain.reembolso.model.Usuario
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

data class UsuarioSummaryDTO(
        val id: IdUsuario,
        val nome: String,
        val cpf: String
) {

    companion object {

        fun toDTO(usuario: Usuario) = UsuarioSummaryDTO(
                id = usuario.id,
                nome = usuario.nome,
                cpf = usuario.cpf
        )

    }

}

data class UsuarioDetailsDTO(
        val id: IdUsuario,
        val nome: String,
        val cpf: String
) {

    companion object {

        fun toDTO(usuario: Usuario) = UsuarioDetailsDTO(
                id = usuario.id,
                nome = usuario.nome,
                cpf = usuario.cpf
        )

    }

}

data class UsuarioDTO(

        @field:NotBlank(message = "O nome deve ser informado")
        @field:Size(
                min = MIN_NAME_SIZE,
                max = MAX_NAME_SIZE,
                message = "O nome deve ter entre $MIN_NAME_SIZE e $MAX_NAME_SIZE caracteres"
        )
        val nome: String,

        @field:NotBlank(message = "O cpf deve ser informado")
        val cpf: String

) {

    fun toModel(id: IdUsuario) = Usuario(
            id = id,
            nome = this.nome,
            cpf = this.cpf
    )

    companion object {

        fun toDTO(usuario: Usuario) = UsuarioDTO(
                nome = usuario.nome,
                cpf = usuario.cpf
        )
    }

}