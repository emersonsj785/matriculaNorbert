package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Docente;

@Repository
public interface IDocenteRepositorio extends JpaRepository<Docente, Long>
{
	
}
