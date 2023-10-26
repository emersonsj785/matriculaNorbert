package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Seccion;

@Repository
public interface ISeccionRepositorio extends JpaRepository<Seccion, Long>
{
	
}
