package com.matricula.colegio.entidad;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    
    @NotBlank(message = "Los Apellidos no puede ser vacio.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Los Apellidos deben contener solo letras.")
    private String apellidos;
    
    @NotBlank(message = "Los Nombres no puede ser vacio.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Los Nombres deben contener solo letras.")
    private String nombres;
    
    @NotBlank(message = "El Correo no puede ser vacio.")
    @Email(message = "El Correo electrónico no es válido.")
    private String correo;
    
    @NotBlank(message = "El Grado no puede ser vacio.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El Grado debe contener solo letras.")
    private String grado;
    
    @NotBlank(message = "El Nivel no puede ser vacio.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El Nivel debe contener solo letras.")
    private String nivel;
    
    @NotBlank(message = "La contraseña no puede ser vacio.")
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