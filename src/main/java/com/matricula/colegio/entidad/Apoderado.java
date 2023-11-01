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
public class Apoderado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Apoderado;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;


    private String parentesco;
    private String ocupacion;
    private String direccion_Residencia;
    
    
	public Apoderado(String parentesco, String ocupacion, String direccion_Residencia)
	{
		super();
		this.parentesco = parentesco;
		this.ocupacion = ocupacion;
		this.direccion_Residencia = direccion_Residencia;
	}
    
    
    
    
}