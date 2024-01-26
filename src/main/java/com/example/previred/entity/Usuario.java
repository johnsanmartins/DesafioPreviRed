package com.example.previred.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@NotBlank
	private String nombre;

	@Email
	private String email;

	@NotBlank
	private String password;

	private LocalDateTime created;

	private LocalDateTime modified;

	private LocalDateTime lastLogin;

	private String token;

	private boolean active;

}
