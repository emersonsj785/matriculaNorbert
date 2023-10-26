package com.matricula.colegio.servicio;

import java.util.List;

import com.matricula.colegio.entidad.DatosAlumno;



public interface IDatosAlumnoServicio
{
	public List<DatosAlumno> listarTodosDatos();

	public DatosAlumno guardarDatos(DatosAlumno datos);

	public DatosAlumno obtenerDatos(Long id);

	public DatosAlumno actualizarDatos(DatosAlumno datos);

	public void eliminarDatos(Long id);
}
