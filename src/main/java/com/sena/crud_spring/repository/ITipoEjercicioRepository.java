package com.sena.crud_spring.repository;

import com.sena.crud_spring.model.TipoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoEjercicioRepository extends JpaRepository<TipoEjercicio, Long> {
}
