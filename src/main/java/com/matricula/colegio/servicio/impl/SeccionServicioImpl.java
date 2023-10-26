package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Seccion;
import com.matricula.colegio.repositorio.ISeccionRepositorio;
import com.matricula.colegio.servicio.ISeccionServicio;



@Service
public class SeccionServicioImpl implements ISeccionServicio
{

	@Autowired 
	private ISeccionRepositorio seccionRepositorio;

	@Override
	public List<Seccion> listarTodosSeccion()
	{
		return seccionRepositorio.findAll();
	}

	@Override
	public Seccion guardarSeccion(Seccion seccion)
	{
		return seccionRepositorio.save(seccion);
	}

	@Override
	public Optional<Seccion> obtenerSeccionPorId(Long id)
	{
		return seccionRepositorio.findById(id);
	}

	@Override
	public Seccion actualizarSeccion(Long id, Seccion seccion)
	{
		Optional<Seccion> existeSeccion = seccionRepositorio.findById(id);
		if(existeSeccion.isPresent())
		{
			seccion.setId_Seccion(existeSeccion.get().getId_Seccion());
			return seccionRepositorio.save(seccion);
		}else
		{
			throw new RuntimeException("No se encontró la sección con el ID proporcionado");
		}
	}

	@Override
	public void eliminarSeccion(Long id)
	{
		seccionRepositorio.deleteById(id);
		
	}
	
	
	

}
