package com.matricula.colegio.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Usuario;

	@ManyToOne
	@JoinColumn(name = "id_Perfil")
	private Perfil perfil;

	private String dni;

	private String apellidos;
	private String nombres;
	private String telefono;
	private String correo;
	private String contrasenia;
	private String fecha_Nacimiento;
	private String sexo;
	
	public Usuario(Perfil perfil, String dni, String apellidos, String nombres, String telefono, String correo,
			String contrasenia, String fecha_Nacimiento, String sexo) {
		super();
		this.perfil = perfil;
		this.dni = dni;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.telefono = telefono;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.sexo = sexo;
	}

   
}