package com.example.previred.controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.previred.entity.Usuario;
import com.example.previred.exception.UserAlreadyExistsException;
import com.example.previred.exception.UsuarioNotFoundException;
import com.example.previred.service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	
	private final UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
		List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("buscar/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
		try {
			Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
			return ResponseEntity.ok(usuario);
		} catch (UsuarioNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping(value = "/crearUsuario")
	 public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {

            // Llamar al servicio para crear el usuario
            Usuario createdUser = usuarioService.createUser(usuario);

            // Retornar la respuesta con el usuario creado
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyExistsException e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }

	@PutMapping("actualizar/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable UUID id, @RequestBody Usuario nuevoUsuario) {
		try {
			Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, nuevoUsuario);
			return ResponseEntity.ok(usuarioActualizado);
		} catch (UsuarioNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {
		try {
			usuarioService.eliminarUsuario(id);
			return ResponseEntity.noContent().build();
		} catch (UsuarioNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
