package com.example.previred.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.previred.entity.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	 boolean existsByEmail(String email);

	Optional<Usuario> findById(UUID id);
}
