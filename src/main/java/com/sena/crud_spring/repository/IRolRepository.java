package com.sena.crud_spring.repository;


import com.sena.crud_spring.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRolRepository extends JpaRepository<Rol, Long> {
}

