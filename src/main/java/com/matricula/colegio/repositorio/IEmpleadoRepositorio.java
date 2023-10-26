package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Empleado;



@Repository
public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Long>
{
	
}
