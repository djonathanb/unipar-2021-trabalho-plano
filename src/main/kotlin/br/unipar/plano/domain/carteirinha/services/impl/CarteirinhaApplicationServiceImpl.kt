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
import br.unipar.plano.interfaces.rest.carteirinha.CarteirinhaDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarteirinhaApplicationServiceImpl(
        private val carteirinhaQueryService: CarteirinhaQueryService,
        private val verificaCarteirinhaUseCase: VerificaCarteirinhaUseCase,
        private val criaCarteirinhaUseCase: CriaCarteirinhaUseCase,
        private val carteirinhaRepository: CarteirinhaRepository
        ): CarteirinhaApplicationService {
    override fun criar(dto: CarteirinhaDTO): String {
        val carteirinha = toModel(dto)
        val novaCarteirinha = criaCarteirinhaUseCase.cria(carteirinha = carteirinha)
        return novaCarteirinha.numeroCarteirinha
    }

    override fun validaCarteirinha(dto: CarteirinhaDTO): Carteirinha {
        var carteirinha : Carteirinha? = null;
        try {
             carteirinha = carteirinhaQueryService.buscaPorId(dto.numeroCarteirinha)
        } catch (ex: Exception) {
            throw ex;
        }

        if (carteirinha.validate())
            return carteirinha

        throw Exception("Carteirinha Inválida:\n Status: ${carteirinha.status}")
    }

    override fun registraExtravio(idUsuario: Int, motivo: String): Carteirinha {
        throw Exception()
    }

    override fun registraEntrega(dto: CarteirinhaDTO): Carteirinha {
        var carteirinha : Carteirinha? = null;
        try {
            carteirinha = carteirinhaQueryService.buscaPorId(dto.numeroCarteirinha)
        } catch (ex: Exception) {
            throw ex;
        }
        carteirinha = carteirinha.registrarEntrega()
        return carteirinhaRepository.save(carteirinha)

    }

    fun toModel(carteirinhaDTO: CarteirinhaDTO): Carteirinha = Carteirinha(
            numeroCarteirinha = carteirinhaDTO.numeroCarteirinha,
            idUsuario = carteirinhaDTO.idUsuario,
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