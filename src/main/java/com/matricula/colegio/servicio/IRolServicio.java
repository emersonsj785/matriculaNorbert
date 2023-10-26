package com.matricula.colegio.servicio;

import java.util.List;

import com.matricula.colegio.entidad.Rol;

public interface IRolServicio
{
	public List<Rol> listarTodosRoles();
	
	public Rol guardarRol(Rol rol);
	
	public Rol obtenerRolPorId(Long id);
	
	public Rol atualizarRol(Rol rol);
	
	public void eliminarRol(Long id);
}
