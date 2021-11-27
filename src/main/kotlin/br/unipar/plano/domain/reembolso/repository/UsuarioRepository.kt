package br.unipar.plano.domain.reembolso.repository

import br.unipar.plano.domain.reembolso.model.IdUsuario
import br.unipar.plano.domain.reembolso.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, IdUsuario> {}