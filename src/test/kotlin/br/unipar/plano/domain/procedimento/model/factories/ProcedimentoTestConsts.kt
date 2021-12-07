package br.unipar.plano.domain.procedimento.model.factories

import br.unipar.plano.application.factories.CIDADE_TOLEDO_ID
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.procedimento.model.Especialidade
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Servico
import br.unipar.plano.domain.procedimento.model.StatusProcedimento
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

val PROCEDIMENTO_CO_ID = IdProcedimento(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val PROCEDIMENTO_CO_VALOR = BigDecimal.valueOf(100)
val PROCEDIMENTO_CO_STATUS = StatusProcedimento.PENDENTE
val PROCEDIMENTO_CO_DATACANC = null
val PROCEDIMENTO_CO_DATAPROC = LocalDate.now()
val PROCEDIMENTO_CO_SERVICO  = Servico.CARDIOLOGIA_PEDIATRICA
val PROCEDIMENTO_CO_EPECIALIDADE = Especialidade.CLINICA_MEDICA
val PROCEDIMENTO_CO_CARTEIRINHA_ID = PROCEDIMENTO_CO_ID


