package com.matricula.colegio.entidad.dto;
import java.util.Date;
import java.util.List;

import com.matricula.colegio.entidad.Alumno;
import com.matricula.colegio.entidad.Apoderado;
import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.entidad.DocenteCursoSeccion;
import com.matricula.colegio.entidad.Seccion;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FichaMatriculaDto
{

	private Long idFicha;
	private Apoderado apoderado;
	private Alumno alumno;
	private Seccion seccion;
    private String periodo;
    private Date fecha;
    private String estado;
    private String docenteCurso;
    private List<DocenteCursoSeccion> docenteCursoSec;
    
    
}