package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Curso;
import com.matricula.colegio.repositorio.ICursoRepositorio;
import com.matricula.colegio.servicio.ICursoServicio;

@Service
public class CursoServicioImpl implements ICursoServicio
{

	@Autowired 
	private ICursoRepositorio cursoRepositorio;

	@Override
	public List<Curso> listarTodosCursos()
	{
		return cursoRepositorio.findAll();
	}

	@Override
	public Curso guardarCurso(Curso curso)
	{
		return cursoRepositorio.save(curso);
	}

	@Override
	public Optional<Curso> obtenerCursoPorId(Long id)
	{
		return cursoRepositorio.findById(id);
	}

	@Override
	public Curso atualizarCurso(Long id, Curso curso)
	{
		Optional<Curso> existeCurso = cursoRepositorio.findById(id);
		if(existeCurso.isPresent())
		{
			curso.setId_Curso(existeCurso.get().getId_Curso());
			return cursoRepositorio.save(curso);
		}else {
			throw new RuntimeException("No se encontró el curso con el ID proporcionado");
		}
	}

	@Override
	public void eliminarCurso(Long id)
	{
		cursoRepositorio.deleteById(id);	
	}


	
	

}
