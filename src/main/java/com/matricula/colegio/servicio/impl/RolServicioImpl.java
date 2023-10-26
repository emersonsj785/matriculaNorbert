package com.matricula.colegio.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Rol;
import com.matricula.colegio.repositorio.IRolRepositorio;
import com.matricula.colegio.servicio.IRolServicio;

@Service
public class RolServicioImpl implements IRolServicio
{

	@Autowired 
	private IRolRepositorio rolRepositorio;
	
	@Override
	public List<Rol> listarTodosRoles()
	{
		return rolRepositorio.findAll();
	}

	@Override
	public Rol guardarRol(Rol rol)
	{
		return rolRepositorio.save(rol);
	}

	@Override
	public Rol obtenerRolPorId(Long id)
	{
		return rolRepositorio.findById(id).get();
	}

	@Override
	public Rol atualizarRol(Rol rol)
	{
		return rolRepositorio.save(rol);
	}

	@Override
	public void eliminarRol(Long id)
	{
		rolRepositorio.deleteById(id);
	}

}
