package com.matricula.colegio.servicio;

import java.util.List;

import com.matricula.colegio.entidad.Grado;

public interface IGradoServicio
{
	public List<Grado> listarTodosGrados();

	public Grado guardarGrado(Grado grado);

	public Grado obtenerGrado(Long id);

	public Grado actualizarGrado(Grado grado);

	public void eliminarGrado(Long id);
}
