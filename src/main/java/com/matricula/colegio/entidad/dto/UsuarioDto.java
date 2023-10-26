package com.matricula.colegio.entidad.dto;
import com.matricula.colegio.entidad.Perfil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto
{
	
    private Long id_Usuario;
    private Perfil perfil;
    private String dni;
    private String apellidos;
    private String nombres;
    private String telefono;
    private String correo;
    private String contrasenia;
    private String fecha_Nacimiento;
    private String sexo;
    
    
	


	public UsuarioDto(String correo) {
		super();
		this.correo = correo;
	}





	public UsuarioDto(Perfil perfil, String dni, String apellidos, String nombres, String telefono, String correo,
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