package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;
import com.matricula.colegio.entidad.DocenteCurso;

public interface IDocenteCursoServicio
{	
	public List<DocenteCurso> listarTodosDocenteCursos();

	public DocenteCurso guardarDocenteCurso(DocenteCurso docenteCurso);

	public Optional<DocenteCurso> obtenerDocenteCursoPorId(Long id);

	public DocenteCurso actualizarDocenteCurso(Long id, DocenteCurso docenteCurso);

	public void eliminarDocenteCurso(Long id);
}
