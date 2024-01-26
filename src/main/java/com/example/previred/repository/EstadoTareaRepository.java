package com.example.previred.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.previred.entity.EstadoTarea;
@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {
}
