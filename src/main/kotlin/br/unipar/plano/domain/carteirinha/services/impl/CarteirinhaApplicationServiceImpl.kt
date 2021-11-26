package br.unipar.plano.domain.carteirinha.services.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import br.unipar.plano.domain.carteirinha.model.StatusCarteirinha
import br.unipar.plano.domain.carteirinha.services.CarteirinhaApplicationService
import br.unipar.plano.domain.carteirinha.services.CarteirinhaQueryService
import br.unipar.plano.domain.carteirinha.usecases.CriaCarteirinhaUseCase
import br.unipar.plano.domain.carteirinha.usecases.VerificaCarteirinhaUseCase
import br.unipar.plano.domain.carteirinha.usecases.impl.CriaCarteirinhaUseCaseImpl
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.usuario.IdUsuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarteirinhaApplicationServiceImpl(
        private val carteirinhaQueryService: CarteirinhaQueryService,
        private val verificaCarteirinhaUseCase: VerificaCarteirinhaUseCase,
        private val criaCarteirinhaUseCase: CriaCarteirinhaUseCase,
        private val carteirinhaRepository: CarteirinhaRepository
        ): CarteirinhaApplicationService {
    override fun criar(idUsuario: IdUsuario): IdCarteirinha {
        val carteirinha = toModel(idUsuario)
        val novaCarteirinha = criaCarteirinhaUseCase.cria(carteirinha = carteirinha)
        return novaCarteirinha.numeroCarteirinha
    }

    override fun validaCarteirinha(idCarteirinha: IdCarteirinha): Carteirinha {
        var carteirinha : Carteirinha? = null;
        try {
             carteirinha = carteirinhaQueryService.buscaPorId(idCarteirinha)
        } catch (ex: Exception) {
            throw ex;
        }

        if (carteirinha.validate())
            return carteirinha

        throw Exception("Carteirinha Inválida:\n Status: ${carteirinha.status}")
    }

    override fun registraExtravio(idUsuario: IdUsuario, motivo: String): Carteirinha {
        throw Exception()
    }

    override fun registraEntrega(idCarteirinha: IdCarteirinha): Carteirinha {
        var carteirinha : Carteirinha? = null;
        try {
            carteirinha = carteirinhaQueryService.buscaPorId(idCarteirinha)
        } catch (ex: Exception) {
            throw ex;
        }
        carteirinha = carteirinha.registrarEntrega()
        return carteirinhaRepository.save(carteirinha)

    }

    fun toModel(idUsuario: IdUsuario): Carteirinha = Carteirinha(
            numeroCarteirinha = IdCarteirinha(),
            idUsuario = idUsuario,
            dataEmissao = Date(),
            dataVencimento = calcularVencimento(),
            dataEntrega = null,
            status = StatusCarteirinha.ENTREGA_PENDENTE
    )

    fun calcularVencimento(): Date {
        var date = Calendar.getInstance()
        date.add(Calendar.YEAR, 3)
        return date.time
    }

}