package com.matricula.colegio.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Curso
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Curso;
    
    @ManyToOne
    @JoinColumn(name = "id_Grado")
    private Grado grado;


    private String nombre;
    private String descripcion;
    private String nivel;
    private String periodo;
    
	public Curso(String nombre, String descripcion, String nivel, String periodo)
	{
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.periodo = periodo;
	}

    
}