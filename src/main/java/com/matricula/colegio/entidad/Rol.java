package com.matricula.colegio.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Rol
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Rol;
	private String nombre_Rol;
	private String descripcion;
	
	// Constructor que acepta dos parámetros de tipo String
    public Rol(String nombre_Rol, String descripcion) {
        this.nombre_Rol = nombre_Rol;
        this.descripcion = descripcion;
    }

    // Constructor sin argumentos (necesario para JPA)
    public Rol() {
    }
}
