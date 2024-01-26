package com.example.previred.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank
	private String nombreTarea;
	
	@NotBlank
	private String descripcion;

	private LocalDateTime created;

	private LocalDateTime modified;

	private LocalDateTime lastLogin;

	private String token;

	private boolean active;
	
	public List<EstadoTarea> getEstadoTareas() {
		return estadoTarea;
	}

	public void setEstadoTareas(List<EstadoTarea> estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	@OneToMany(cascade = CascadeType.ALL)
	private List<EstadoTarea> estadoTarea;


}
