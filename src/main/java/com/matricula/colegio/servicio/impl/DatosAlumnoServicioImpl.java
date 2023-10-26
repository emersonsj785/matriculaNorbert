package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.DatosAlumno;
import com.matricula.colegio.repositorio.IDatosAlumnoRepositorio;
import com.matricula.colegio.servicio.IDatosAlumnoServicio;



@Service
public class DatosAlumnoServicioImpl implements IDatosAlumnoServicio
{

	@Autowired 
	private IDatosAlumnoRepositorio datosAlumnoRepositorio;

	@Override
	public List<DatosAlumno> listarTodosDatos()
	{
		return datosAlumnoRepositorio.findAll();
	}

	@Override
	public DatosAlumno guardarDatos(DatosAlumno datos)
	{
		return datosAlumnoRepositorio.save(datos);
	}

	@Override
	public DatosAlumno obtenerDatos(Long id)
	{
		Optional<DatosAlumno> resultado= datosAlumnoRepositorio.findById(id);
		return resultado.orElse(null);
	}

	@Override
	public DatosAlumno actualizarDatos(DatosAlumno datos)
	{
		return datosAlumnoRepositorio.save(datos);
	}

	@Override
	public void eliminarDatos(Long id)
	{
		datosAlumnoRepositorio.deleteById(id);
	}

}
