package br.unipar.plano.domain.credenciamentos.usecases.impl

import br.unipar.plano.domain.credenciamentos.model.Especialidade
import br.unipar.plano.domain.credenciamentos.model.EspecialidadeRepository
import br.unipar.plano.domain.credenciamentos.usecases.CriaEspecialidadeUseCase
import org.springframework.stereotype.Service


@Service
class CriaEspecialidadeUseCaseImpl(private val especialidadeRepository: EspecialidadeRepository) : CriaEspecialidadeUseCase {

    override fun executa(especialidade: Especialidade): Especialidade {
        return especialidadeRepository.save(especialidade)
    }
}