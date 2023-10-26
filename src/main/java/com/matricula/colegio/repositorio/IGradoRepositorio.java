package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Grado;

@Repository
public interface IGradoRepositorio extends JpaRepository<Grado, Long>
{
	
}
