package com.matricula.colegio.entidad;

import java.time.LocalTime;

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
public class Seccion
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Seccion;

    private String nombre;
    private String descripcion;
    private String dias_Semana;
    private LocalTime hora;
    private Integer aforo;
    
	public Seccion(String nombre, String descripcion, String dias_Semana, LocalTime hora, Integer aforo)
	{
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dias_Semana = dias_Semana;
		this.hora = hora;
		this.aforo = aforo;
	}
    
    

    
}