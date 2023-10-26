package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.DocenteCurso;

@Repository
public interface IDocenteCursoRepositorio extends JpaRepository<DocenteCurso, Long>
{
	
}
