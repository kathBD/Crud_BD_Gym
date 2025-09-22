package com.sena.crud_spring.repository;

import com.sena.crud_spring.model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEjercicioRepository extends JpaRepository<Ejercicio, Long> {
}
