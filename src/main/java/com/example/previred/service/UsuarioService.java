package com.example.previred.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.previred.entity.Usuario;
import com.example.previred.exception.UserAlreadyExistsException;
import com.example.previred.exception.UsuarioNotFoundException;
import com.example.previred.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> obtenerTodosUsuarios() {
        return userRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario actualizarUsuario(UUID id, Usuario nuevoUsuario) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        // Lógica para actualizar campos del usuario existente con los del nuevo usuario
        usuarioExistente.setNombre(nuevoUsuario.getNombre());
        // Actualiza otros campos según sea necesario
        return userRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(UUID id) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        userRepository.delete(usuarioExistente);
    }
    

	public Usuario createUser(Usuario user) {

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("El correo ya registrado");
		}

		String token = generateToken();

		LocalDateTime now = LocalDateTime.now();
		
		user.setNombre(user.getNombre());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setCreated(now);
		user.setModified(now);
		user.setLastLogin(now);

		user.setToken(token);
		user.setActive(true);

		return userRepository.save(user);
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

}
