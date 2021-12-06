package br.unipar.plano.domain.transportes.services.impl

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.services.TransporteApplicationService
import br.unipar.plano.domain.centrais.services.TransporteQueryService
import br.unipar.plano.domain.centrais.usecases.AprovaTransporteUseCase
import br.unipar.plano.domain.centrais.usecases.CancelaTransporteUseCase
import br.unipar.plano.domain.centrais.usecases.CriaTransporteUseCase
import br.unipar.plano.domain.transportes.model.EnderecoTransporte
import br.unipar.plano.domain.transportes.model.StatusTransporte
import br.unipar.plano.domain.transportes.model.Transporte
import br.unipar.plano.interfaces.rest.carteirinhas.CarteirinhaSummaryDTO
import br.unipar.plano.interfaces.rest.transportes.EnderecoTransporteDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteDetailsDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteSummaryDTO
import org.springframework.stereotype.Service

@Service
class TransporteApplicationServiceImpl(
    private val transporteQueryService: TransporteQueryService,
    private val criaTransporteUseCase: CriaTransporteUseCase,
    private val cancelaTransporteUseCase: CancelaTransporteUseCase,
    private val aprovATransporteUseCase: AprovaTransporteUseCase
) : TransporteApplicationService {

    override fun cria(transporteDTO: TransporteDTO): IdTransporte {
        val transporte = toModel(IdTransporte(), transporteDTO)
        val novaTransporte = criaTransporteUseCase.executa(transporte)
        return novaTransporte.id
    }

    override fun atualiza(transporteDTO: TransporteDTO): IdTransporte {
        val transporte = toModel(IdTransporte(), transporteDTO)
        val novaTransporte = criaTransporteUseCase.executa(transporte)
        return novaTransporte.id
    }

    override fun deleta(idTransporte: IdTransporte): IdTransporte {
        val transporteDetailsDTO = buscaPorId(idTransporte)
        val transporte = toModel(idTransporte, transporteDetailsDTO.transporteData)
        val novaTransporte = criaTransporteUseCase.executa(transporte)
        return novaTransporte.id
    }

    override fun cancela(transporteDTO: TransporteDTO): IdTransporte {
        val transporte = changeStatusModel(IdTransporte(), transporteDTO, StatusTransporte.CANCELADO)
        val novaTransporte = cancelaTransporteUseCase.executa(transporte)
        return novaTransporte.id
    }

    override fun aprova(transporteDTO: TransporteDTO): IdTransporte {
        val transporte = changeStatusModel(IdTransporte(), transporteDTO, StatusTransporte.APROVADO)
        val novaTransporte = aprovATransporteUseCase.executa(transporte)
        return novaTransporte.id
    }

    override fun lista(): List<TransporteSummaryDTO> = transporteQueryService.lista().map {
        toSummaryDTO(it)
    }

    override fun buscaPorId(idTransporte: IdTransporte): TransporteDetailsDTO =
        toDetailsDTO(transporteQueryService.buscaPorId(idTransporte))

    private fun toModel(id: IdTransporte, transporteDTO: TransporteDTO) = Transporte(
        id = id,
        carteirinha = Carteirinha(id = transporteDTO.carteirinha.id),
        enderecoOrigem = EnderecoTransporte(
            idTransporte = id,
            cidade = transporteDTO.enderecoOrigem.cidade,
            cep = transporteDTO.enderecoOrigem.cep,
            bairro = transporteDTO.enderecoOrigem.bairro,
            logradouro = transporteDTO.enderecoOrigem.logradouro,
            numero = transporteDTO.enderecoOrigem.numero,
            complemento = transporteDTO.enderecoOrigem.complemento
        ),
        enderecoDestino = EnderecoTransporte(
            idTransporte = id,
            cidade = transporteDTO.enderecoDestino.cidade,
            cep = transporteDTO.enderecoDestino.cep,
            bairro = transporteDTO.enderecoDestino.bairro,
            logradouro = transporteDTO.enderecoDestino.logradouro,
            numero = transporteDTO.enderecoDestino.numero,
            complemento = transporteDTO.enderecoDestino.complemento
        ),
        tipoTransporte = transporteDTO.tipoTransporte,
    )

    private fun changeStatusModel(id: IdTransporte, transporteDTO: TransporteDTO, status: StatusTransporte) =
        Transporte(
            id = id,
            carteirinha = Carteirinha(id = transporteDTO.carteirinha.id),
            status = status,
            enderecoOrigem = EnderecoTransporte(
                idTransporte = id,
                cidade = transporteDTO.enderecoOrigem.cidade,
                cep = transporteDTO.enderecoOrigem.cep,
                bairro = transporteDTO.enderecoOrigem.bairro,
                logradouro = transporteDTO.enderecoOrigem.logradouro,
                numero = transporteDTO.enderecoOrigem.numero,
                complemento = transporteDTO.enderecoOrigem.complemento
            ),
            enderecoDestino = EnderecoTransporte(
                idTransporte = id,
                cidade = transporteDTO.enderecoDestino.cidade,
                cep = transporteDTO.enderecoDestino.cep,
                bairro = transporteDTO.enderecoDestino.bairro,
                logradouro = transporteDTO.enderecoDestino.logradouro,
                numero = transporteDTO.enderecoDestino.numero,
                complemento = transporteDTO.enderecoDestino.complemento
            ),
            tipoTransporte = transporteDTO.tipoTransporte,
        )

    private fun toDetailsDTO(transporte: Transporte) = TransporteDetailsDTO(
        id = transporte.id,
        transporteData = toDTO(transporte)
    )

    private fun toDTO(transporte: Transporte) = TransporteDTO(
        carteirinha = CarteirinhaSummaryDTO(
            id = transporte.carteirinha.id,
        ),
        enderecoOrigem = EnderecoTransporteDTO(
            cidade = transporte.enderecoOrigem.cidade,
            cep = transporte.enderecoOrigem.cep,
            bairro = transporte.enderecoOrigem.bairro,
            logradouro = transporte.enderecoOrigem.logradouro,
            numero = transporte.enderecoOrigem.numero,
            complemento = transporte.enderecoOrigem.complemento
        ),
        enderecoDestino = EnderecoTransporteDTO(
            cidade = transporte.enderecoDestino.cidade,
            cep = transporte.enderecoDestino.cep,
            bairro = transporte.enderecoDestino.bairro,
            logradouro = transporte.enderecoDestino.logradouro,
            numero = transporte.enderecoDestino.numero,
            complemento = transporte.enderecoDestino.complemento
        ),
        tipoTransporte = transporte.tipoTransporte,
    )

    private fun toSummaryDTO(transporte: Transporte) = TransporteSummaryDTO(
        id = transporte.id,
        carteirinha = CarteirinhaSummaryDTO(
            id = transporte.carteirinha.id,
        ),
    )
}