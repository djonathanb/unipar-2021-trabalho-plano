package br.unipar.plano.domain.pessoas.services.impl

import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.services.PessoaApplicationService
import br.unipar.plano.domain.pessoas.services.PessoaQueryService
import br.unipar.plano.domain.pessoas.usecases.AtualizaPessoaUseCase
import br.unipar.plano.domain.pessoas.usecases.CriaPessoaUseCase
import br.unipar.plano.domain.pessoas.usecases.DeletaPessoaUseCase
import br.unipar.plano.interfaces.rest.pessoas.PessoaDTO
import br.unipar.plano.interfaces.rest.pessoas.PessoaDetailsDTO
import org.springframework.stereotype.Service

@Service
class PessoaApplicationServiceImpl(
    private val pessoaQueryService: PessoaQueryService,
    private val criaPessoaUseCase: CriaPessoaUseCase,
    private val deletaPessoaUseCase: DeletaPessoaUseCase,
    private val atualizaPessoaUseCase: AtualizaPessoaUseCase
) : PessoaApplicationService {

    override fun cria(pessoaDTO: PessoaDTO): IdPessoa {
        val pessoa = pessoaDTO.toModel((IdPessoa()))
        val novaPessoa = criaPessoaUseCase.cria(pessoa)
        return novaPessoa.idPessoa
    }

    override fun deleta(idPessoa: IdPessoa) {
        deletaPessoaUseCase.executa(idPessoa)
    }

    override fun lista() = pessoaQueryService.lista().map(PessoaDTO::toDTO)

    override fun buscaPorId(idPessoa: IdPessoa): PessoaDetailsDTO {
        val pessoa = pessoaQueryService.buscaPorId(idPessoa)
        return PessoaDetailsDTO.toDTO(pessoa)
    }


    override fun atualiza(idPessoa: IdPessoa, pessoaDTO: PessoaDTO) {
        atualizaPessoaUseCase.executa(idPessoa) {
            it.with(
                nome = pessoaDTO.nome,
                endereco = pessoaDTO.endereco,
                rg = pessoaDTO.rg,
                cpf = pessoaDTO.cpf,
                estadoCivil = pessoaDTO.estadoCivil,
                dataDeNascimento = pessoaDTO.dataDeNascimento,
                nomeMae = pessoaDTO.nomeMae,
                nomePai = pessoaDTO.nomePai
            )
        }
    }

}