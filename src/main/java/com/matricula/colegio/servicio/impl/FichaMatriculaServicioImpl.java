package com.matricula.colegio.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matricula.colegio.entidad.FichaMatricula;
import com.matricula.colegio.repositorio.IFichaMatriculaRepositorio;
import com.matricula.colegio.servicio.IFichaMatriculaServicio;

@Service
public class FichaMatriculaServicioImpl implements  IFichaMatriculaServicio
{

	@Autowired 
	private IFichaMatriculaRepositorio fichaMatriculaRepositorio;

	@Override
	public FichaMatricula generarMatricula(FichaMatricula fichaMatricula)
	{
		return fichaMatriculaRepositorio.save(fichaMatricula);
	}

    

}
