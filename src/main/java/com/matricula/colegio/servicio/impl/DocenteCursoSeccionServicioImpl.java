package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.entidad.DocenteCursoSeccion;
import com.matricula.colegio.repositorio.IDocenteCursoRepositorio;
import com.matricula.colegio.repositorio.IDocenteCursoSeccionRepositorio;
import com.matricula.colegio.servicio.IDocenteCursoSeccionServicio;
import com.matricula.colegio.servicio.IDocenteCursoServicio;

@Service
public class DocenteCursoSeccionServicioImpl implements IDocenteCursoSeccionServicio
{

	@Autowired 
	private IDocenteCursoSeccionRepositorio docenteCursoSeccionRepositorio;

	@Override
	public List<DocenteCursoSeccion> listarTodasDocenteCursoSeccion()
	{
		return docenteCursoSeccionRepositorio.findAll();
	}

	@Override
	public DocenteCursoSeccion guardarDocenteCursoSeccion(DocenteCursoSeccion docenteCursoSeccion)
	{
		return docenteCursoSeccionRepositorio.save(docenteCursoSeccion);
	}

	@Override
	public Optional<DocenteCursoSeccion> obtenerDocenteCursoSeccionPorId(Long id)
	{
		return docenteCursoSeccionRepositorio.findById(id);
	}

	@Override
	public DocenteCursoSeccion actualizarDocenteCursoSeccion(Long id, DocenteCursoSeccion docenteCursoSeccion)
	{
		if (docenteCursoSeccionRepositorio.existsById(id))
		{
            docenteCursoSeccion.setId(id);
            return docenteCursoSeccionRepositorio.save(docenteCursoSeccion);
        }
        return null; // Manejo de errores, por ejemplo, lanzar una excepción.
	}

	@Override
	public void eliminarDocenteCursoSeccion(Long id)
	{
		docenteCursoSeccionRepositorio.deleteById(id);
	}
	
	
}
