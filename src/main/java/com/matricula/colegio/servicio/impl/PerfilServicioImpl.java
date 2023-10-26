package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Perfil;
import com.matricula.colegio.repositorio.IPerfilRepositorio;
import com.matricula.colegio.servicio.IPerfilServicio;

@Service
public class PerfilServicioImpl implements IPerfilServicio
{

	@Autowired 
	private IPerfilRepositorio perfilRepositorio;

	@Override
    public List<Perfil> obtenerTodosLosPerfiles() {
        return perfilRepositorio.findAll();
    }

    @Override
    public Optional<Perfil> obtenerPerfilPorId(Long id) {
        return perfilRepositorio.findById(id);
    }

    @Override
    public Perfil crearPerfil(Perfil perfil) {
        return perfilRepositorio.save(perfil);
    }

    @Override
    public Perfil actualizarPerfil(Long id, Perfil perfil)
    {
        Optional<Perfil> existingPerfil = perfilRepositorio.findById(id);
        if (existingPerfil.isPresent()) {
            perfil.setId_Perfil(existingPerfil.get().getId_Perfil());
            return perfilRepositorio.save(perfil);
        } else {
            throw new RuntimeException("No se encontró el perfil con el ID proporcionado");
        }
    }

    @Override
    public void eliminarPerfil(Long id) {
    	perfilRepositorio.deleteById(id);
    }
	
	

}
