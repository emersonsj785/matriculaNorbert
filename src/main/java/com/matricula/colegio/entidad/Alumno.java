package com.matricula.colegio.entidad;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Alumno;
    
    @OneToOne
    @JoinColumn(name = "id_Dato")
    private DatosAlumno datosAlumno;

    private String apellidos;
    private String nombres;
    private String correo;
    private String grado;
    private String nivel;
    private String contrasenia;
	public Alumno(String apellidos, String nombres, String correo, String grado, String nivel, String contrasenia) {
		super();
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.correo = correo;
		this.grado = grado;
		this.nivel = nivel;
		this.contrasenia = contrasenia;
	}
    
    
    
}