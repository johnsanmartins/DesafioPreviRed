package com.example.previred.exception;

public class TareaNotFoundException extends RuntimeException {

    public TareaNotFoundException(Integer id) {
        super("Tarea no encontrada con el ID: " + id);
    }

	public TareaNotFoundException(String tarea) {
		 super("Tarea no encontrada con el ID: " + tarea);
	}
}