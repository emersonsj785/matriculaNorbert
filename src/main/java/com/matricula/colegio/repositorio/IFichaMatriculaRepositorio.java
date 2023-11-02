package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.FichaMatricula;



@Repository
public interface IFichaMatriculaRepositorio extends JpaRepository<FichaMatricula, Long>
{
	
}
