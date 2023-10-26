package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;

import com.matricula.colegio.entidad.Curso;

public interface ICursoServicio
{
	public List<Curso> listarTodosCursos();
	
	public Curso guardarCurso(Curso curso);
	
	public Optional<Curso> obtenerCursoPorId(Long id);
	
	public Curso atualizarCurso(Long id, Curso curso);
	
	public void eliminarCurso(Long id);
}
