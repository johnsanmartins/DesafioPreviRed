package com.example.previred.exception;

import java.util.UUID;

public class UsuarioNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(UUID id) {
        super("Usuario no encontrado con el ID: " + id);
    }
    
    
}

