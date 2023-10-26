package com.matricula.colegio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "datos_alumno")
public class DatosAlumno
{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Dato")
    private int idDato;

    @Lob
    @Column(name = "foto_DNI")
    private byte[] fotoDNI;

    @Lob
    @Column(name = "foto_Libreta")
    private byte[] fotoLibreta;
    
    @Transient // Esta anotación indica que este campo no debe persistirse en la base de datos.
    private String fotoDNIBase64;
    
    @Transient // Esta anotación indica que este campo no debe persistirse en la base de datos.
    private String fotoLibretaBase64;

	public DatosAlumno(byte[] fotoDNI, byte[] fotoLibreta) {
		super();
		this.fotoDNI = fotoDNI;
		this.fotoLibreta = fotoLibreta;
	}
    
    
    
}