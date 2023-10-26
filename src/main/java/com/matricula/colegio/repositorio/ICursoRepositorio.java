package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Curso;

@Repository
public interface ICursoRepositorio extends JpaRepository<Curso, Long>
{
	
}
