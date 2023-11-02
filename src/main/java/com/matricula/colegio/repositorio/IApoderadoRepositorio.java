package com.matricula.colegio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matricula.colegio.entidad.Apoderado;
import com.matricula.colegio.entidad.Usuario;

@Repository
public interface IApoderadoRepositorio extends JpaRepository<Apoderado, Long>
{
	Optional<Apoderado> findByUsuario(Usuario usuario);
}
