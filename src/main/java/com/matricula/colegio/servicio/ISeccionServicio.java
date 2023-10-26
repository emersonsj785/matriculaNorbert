package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;

import com.matricula.colegio.entidad.Seccion;



public interface ISeccionServicio
{
	public List<Seccion> listarTodosSeccion();
	
	public Seccion guardarSeccion(Seccion seccion);
	
	public Optional<Seccion> obtenerSeccionPorId(Long id);
	
	public Seccion actualizarSeccion(Long id, Seccion seccion);
	
	public void eliminarSeccion(Long id);
}
