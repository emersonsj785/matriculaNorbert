package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Grado;
import com.matricula.colegio.repositorio.IGradoRepositorio;
import com.matricula.colegio.servicio.IGradoServicio;

@Service
public class GradoServicioImpl implements IGradoServicio
{

	@Autowired 
	private IGradoRepositorio gradoRepositorio;

	@Override
    public List<Grado> listarTodosGrados()
	{
        return gradoRepositorio.findAll();
    }

    @Override
    public Grado guardarGrado(Grado grado)
    {
        return gradoRepositorio.save(grado);
    }

    @Override
    public Grado obtenerGrado(Long id)
    {
        Optional<Grado> gradoOptional = gradoRepositorio.findById(id);
        return gradoOptional.orElse(null);
    }

    @Override
    public Grado actualizarGrado(Grado grado)
    {
        return gradoRepositorio.save(grado);
    }

    @Override
    public void eliminarGrado(Long id)
    {
    	gradoRepositorio.deleteById(id);
    }



	

}
