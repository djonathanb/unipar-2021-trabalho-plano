package br.unipar.plano.domain.transportes.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.IdTransporte

class TransporteNotFoundException(idTransporte: IdTransporte) : NotFoundException("Transporte com id ${idTransporte.id} não encontrado")
