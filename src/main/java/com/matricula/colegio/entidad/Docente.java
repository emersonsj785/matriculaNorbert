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
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Docente;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;


    private String especializacion;
    private String nivel_Educativo;
    private String titulo_Academico;
    
    
	public Docente(String especializacion, String nivel_Educativo, String titulo_Academico)
	{
		super();
		this.especializacion = especializacion;
		this.nivel_Educativo = nivel_Educativo;
		this.titulo_Academico = titulo_Academico;
	}
    
    
    
    
}