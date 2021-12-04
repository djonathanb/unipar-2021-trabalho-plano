package br.unipar.plano.domain.dependentes.model

import br.unipar.plano.domain.pessoas.model.StatusEstadoCivil
import br.unipar.plano.domain.pessoas.model.factories.PESSOA_CO_ID
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DependenteTest {

    @Test
    fun `deve criar com estado civil igual a AMASIADO`() {
        val pessoa = pessoaTest()
        Assertions.assertEquals(StatusEstadoCivil.AMASIADO, pessoa.estadoCivil)
    }

    @Test
    fun `deve permitir a alteracao de informacoes de pessoa`() {
        val novoNome = "JURICLEISON ANDREAS"
        val novoEndereco = "RUA PIQUIRI, 132"
        val novoEstadoCivil = StatusEstadoCivil.CASADO


        val pessoa = pessoaTest()
        val informacaoNova = pessoa.with(
            nome = novoNome,
            estadoCivil = novoEstadoCivil,
            endereco = novoEndereco
        )

        Assertions.assertNotSame(pessoa, informacaoNova)
        Assertions.assertEquals(PESSOA_CO_ID, informacaoNova.idPessoa)
        Assertions.assertEquals(novoEndereco, informacaoNova.endereco)
        Assertions.assertEquals(novoEstadoCivil, informacaoNova.estadoCivil)
        Assertions.assertEquals(novoNome, informacaoNova.nome)
        Assertions.assertEquals(novoEndereco, informacaoNova.endereco)
    }

}