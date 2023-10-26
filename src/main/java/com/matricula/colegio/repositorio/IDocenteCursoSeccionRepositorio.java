package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.DocenteCursoSeccion;



@Repository
public interface IDocenteCursoSeccionRepositorio extends JpaRepository<DocenteCursoSeccion, Long>
{
	
}
