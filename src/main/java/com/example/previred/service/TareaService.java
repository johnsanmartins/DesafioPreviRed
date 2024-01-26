package com.example.previred.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.previred.entity.Tarea;
import com.example.previred.exception.TareaNotFoundException;
import com.example.previred.repository.TareaRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> obtenerTodasTareas() {
        return tareaRepository.findAll();
    }

    public Tarea obtenerTareaPorId(Integer id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException(id));
    }

    public  Tarea crearTarea(Tarea tarea) {

  		String token = generateToken();

  		LocalDateTime now = LocalDateTime.now();
  		tarea.setCreated(now);
  		tarea.setModified(now);
  		tarea.setLastLogin(now);

  		tarea.setToken(token);
  		tarea.setActive(true);

  		 return tareaRepository.save(tarea);
  	}

  	private String generateToken() {

  		String secretKey = "secreto123";

  		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  		LocalDateTime now = LocalDateTime.now();
  		LocalDateTime expirationTime = now.plusDays(1); // Token expirará en 1 día

  		return Jwts.builder().setSubject("user").setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())) // Fecha
  																														// emisión
  				.setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant())) // Fecha de
  																										// expiración
  				.compact();
  	}
    
    
    public Tarea actualizarTarea(Integer id, Tarea nuevaTarea) {
        Tarea tareaExistente = obtenerTareaPorId(id);
        // Lógica para actualizar campos de la tarea existente con los de la nueva tarea
        tareaExistente.setDescripcion(nuevaTarea.getDescripcion());
        // Actualiza otros campos según sea necesario
        return tareaRepository.save(tareaExistente);
    }

    public void eliminarTarea(Integer id) {
        Tarea tareaExistente = obtenerTareaPorId(id);
        tareaRepository.delete(tareaExistente);
    }
}
