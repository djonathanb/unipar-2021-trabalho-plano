package br.unipar.plano.resource.impl

import br.unipar.plano.application.exception.NegocioException
import br.unipar.plano.application.exception.RegistroNaoEncontradoException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandlerResourceImpl {

    @ExceptionHandler(NegocioException::class)
    fun negocioException(exception: NegocioException, request: HttpServletRequest) = ResponseEntity.badRequest().body(exception.message)

    @ExceptionHandler(RegistroNaoEncontradoException::class)
    fun registroNaoEncontradoException(exception: RegistroNaoEncontradoException, request: HttpServletRequest) = ResponseEntity.notFound().build<Void>()
}