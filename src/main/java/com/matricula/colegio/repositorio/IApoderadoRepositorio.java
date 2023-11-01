package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Apoderado;

@Repository
public interface IApoderadoRepositorio extends JpaRepository<Apoderado, Long>
{
	
}
