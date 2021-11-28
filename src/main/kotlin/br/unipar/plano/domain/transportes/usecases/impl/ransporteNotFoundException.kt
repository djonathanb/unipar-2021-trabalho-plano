package br.unipar.plano.domain.centrais.usecases.impl

        import br.unipar.plano.application.exceptions.NotFoundException
        import br.unipar.plano.domain.centrais.model.IdCentral
        import br.unipar.plano.domain.centrais.model.IdTransporte

class TransporteNotFoundException(idTransporte: IdTransporte) : NotFoundException("Central com id ${idTransporte.id} não encontrada")
