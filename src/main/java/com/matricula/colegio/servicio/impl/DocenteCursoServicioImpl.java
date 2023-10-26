package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.repositorio.IDocenteCursoRepositorio;
import com.matricula.colegio.servicio.IDocenteCursoServicio;

@Service
public class DocenteCursoServicioImpl implements IDocenteCursoServicio
{

	@Autowired 
	private IDocenteCursoRepositorio docenteCursoRepositorio;
	
	@Override
    public List<DocenteCurso> listarTodosDocenteCursos()
	{
        return docenteCursoRepositorio.findAll();
    }

    @Override
    public DocenteCurso guardarDocenteCurso(DocenteCurso docenteCurso)
    {
        return docenteCursoRepositorio.save(docenteCurso);
    }

    @Override
    public Optional<DocenteCurso> obtenerDocenteCursoPorId(Long id)
    {
        return docenteCursoRepositorio.findById(id);
    }

    @Override
    public DocenteCurso actualizarDocenteCurso(Long id, DocenteCurso docenteCurso)
    {
        if (docenteCursoRepositorio.existsById(id))
        {
            docenteCurso.setId_DocenteCurso(id);
            return docenteCursoRepositorio.save(docenteCurso);
        }
        return null;
    }

    @Override
    public void eliminarDocenteCurso(Long id)
    {
    	docenteCursoRepositorio.deleteById(id);
    }

}
