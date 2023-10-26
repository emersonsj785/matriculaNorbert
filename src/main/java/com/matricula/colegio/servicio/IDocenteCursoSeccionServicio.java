package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;
import com.matricula.colegio.entidad.DocenteCursoSeccion;

public interface IDocenteCursoSeccionServicio
{
	public List<DocenteCursoSeccion> listarTodasDocenteCursoSeccion();
    
	public DocenteCursoSeccion guardarDocenteCursoSeccion(DocenteCursoSeccion docenteCursoSeccion);
    
	public Optional<DocenteCursoSeccion> obtenerDocenteCursoSeccionPorId(Long id);
    
	public DocenteCursoSeccion actualizarDocenteCursoSeccion(Long id, DocenteCursoSeccion docenteCursoSeccion);
    
	public void eliminarDocenteCursoSeccion(Long id);
}
