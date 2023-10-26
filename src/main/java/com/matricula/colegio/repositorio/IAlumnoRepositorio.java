package com.matricula.colegio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Alumno;
import com.matricula.colegio.entidad.Usuario;



@Repository
public interface IAlumnoRepositorio extends JpaRepository<Alumno, Long>
{
	Optional<Alumno> findByCorreo(String correo);
}
