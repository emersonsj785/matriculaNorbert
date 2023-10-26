package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Rol;

@Repository
public interface IRolRepositorio extends JpaRepository<Rol, Long>
{
	
}
