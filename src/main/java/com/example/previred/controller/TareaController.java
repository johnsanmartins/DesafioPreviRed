package com.example.previred.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.previred.entity.Tarea;
import com.example.previred.service.TareaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodasTareas() {
        List<Tarea> tareas = tareaService.obtenerTodasTareas();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Tarea> buscarPorId(@PathVariable Integer id) {
        Tarea tarea = tareaService.obtenerTareaPorId(id);
        return ResponseEntity.ok(tarea);
    }

	@PostMapping(value = "/crearTarea")
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Integer id, @RequestBody Tarea nuevaTarea) {
        Tarea tareaActualizada = tareaService.actualizarTarea(id, nuevaTarea);
        return ResponseEntity.ok(tareaActualizada);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Integer id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }
}

