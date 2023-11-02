package com.matricula.colegio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ficha_matricula")
public class FichaMatricula {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Ficha_matricula")
    private Long idFicha;
    
    @ManyToOne
    @JoinColumn(name = "id_Apoderado")
    private Apoderado apoderado;
    
    @ManyToOne
    @JoinColumn(name = "id_Alumno")
    private Alumno alumno;
    
    @ManyToOne
    @JoinColumn(name = "id_Seccion")
    private Seccion seccion;


    private String periodo;
    private String fecha;
    private String estado;
    
    
	public FichaMatricula(String periodo, String fecha, String estado)
	{
		super();
		this.periodo = periodo;
		this.fecha = fecha;
		this.estado = estado;
	}
    
    
    
    
}