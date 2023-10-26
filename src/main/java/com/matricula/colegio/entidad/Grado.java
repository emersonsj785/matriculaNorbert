package com.matricula.colegio.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grado
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Grado;
	private String nombre;
	private String descripcion;
	
	
}
