package com.example.previred.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.previred.entity.Tarea;
@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {

}
