package br.unipar.plano.domain.credenciamentos.model.clinicaHospital

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PrestadorClinicaHospitalRepository : JpaRepository<PrestadorClinicaHospital, IdPrestadorClinicaHospital>
