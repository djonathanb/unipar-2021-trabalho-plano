package br.unipar.plano.interfaces.rest

import br.unipar.plano.application.exceptions.NotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [NotFoundException::class])
    fun handleNotFound(notFoundException: NotFoundException, request: WebRequest): ResponseEntity<Any> {
        return handleException(notFoundException, status = HttpStatus.NOT_FOUND, request)
    }

    private fun handleException(exception: Exception, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(exception, ExceptionResponse(exception.message ?: ""), HttpHeaders(), status, request)
    }

}

data class ExceptionResponse(val message: String)
