package br.unipar.plano.domain.carteirinha.services.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import br.unipar.plano.domain.carteirinha.model.StatusCarteirinha
import br.unipar.plano.domain.carteirinha.services.CarteirinhaApplicationService
import br.unipar.plano.domain.carteirinha.services.CarteirinhaQueryService
import br.unipar.plano.domain.carteirinha.usecases.*
import br.unipar.plano.interfaces.rest.carteirinha.CarteirinhaDTO
import br.unipar.plano.interfaces.rest.carteirinha.MotivoDTO
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.regex.Pattern

@Service
class CarteirinhaApplicationServiceImpl(
        private val carteirinhaQueryService: CarteirinhaQueryService,
        private val validaCarteirinhaUseCase: ValidaCarteirinhaUseCase,
        private val registraEntregaUseCase: RegistraCarteirinhaUseCase,
        private val criaCarteirinhaUseCase: CriaCarteirinhaUseCase,
        private val carteirinhaRepository: CarteirinhaRepository,
        private val registraExtravioUseCase: RegistraExtravioUseCase,
        private val registraMotivoExtravioUseCase: RegistraMotivoExtravioUseCase
        ): CarteirinhaApplicationService {
    override fun criar(dto: CarteirinhaDTO): String {
        try {
            validaRegex(dto.numeroCarteirinha)

            val carteirinha = toModel(dto)
            val novaCarteirinha = criaCarteirinhaUseCase.cria(carteirinha = carteirinha)
            return novaCarteirinha.numeroCarteirinha
        } catch (error: Exception) {
            throw error
        }
    }

    override fun validaCarteirinha(numeroCarteirinha: String): Carteirinha {
        validaRegex(numeroCarteirinha)
        return validaCarteirinhaUseCase.validar(numeroCarteirinha)
    }

    override fun registraExtravio(dto: MotivoDTO): MotivoExtravio {

        var carteirinha: Carteirinha? = null;
        carteirinha = carteirinhaQueryService.findByIdUsuario(dto.idUsuario);

        carteirinha = registraExtravioUseCase.registraExtravio(carteirinha)

        var motivoExtravio = toModelMotivo(dto, carteirinha)

        motivoExtravio = registraMotivoExtravioUseCase.RegistraMotivoExtravio(motivoExtravio)

        return motivoExtravio
    }

    override fun registraEntrega(numeroCarteirinha: String): Carteirinha {
        validaRegex(numeroCarteirinha)
        val carteirinha = registraEntregaUseCase.registra(numeroCarteirinha)
        return carteirinha;

    }

    fun toModel(carteirinhaDTO: CarteirinhaDTO, status: StatusCarteirinha = StatusCarteirinha.ENTREGA_PENDENTE): Carteirinha = Carteirinha(
            numeroCarteirinha = carteirinhaDTO.numeroCarteirinha,
            idUsuario = carteirinhaDTO.idUsuario,
            dataEmissao = LocalDate.now(),
            dataVencimento = calcularVencimento(),
            dataEntrega = null,
            status = status
    )

    fun toModelMotivo(motivoDTO: MotivoDTO, carteirinha: Carteirinha): MotivoExtravio = MotivoExtravio(
            numeroCarteirinha = carteirinha.numeroCarteirinha,
            motivoExtravio = motivoDTO.motivo,
            dataExtravio = LocalDate.now()
    )

    fun calcularVencimento(): LocalDate {
        return LocalDate.now().plusYears(3)
    }

    fun validaRegex(value: String): Boolean {
        if (!Pattern.compile("[A-Z]{2}[0-9]{7}[-][0-9]{2}").matcher(value).matches())
            throw Exception("Número de carteirinha não segue padrão determinado! Ex: UF9999999-99")

        return true;
    }

}