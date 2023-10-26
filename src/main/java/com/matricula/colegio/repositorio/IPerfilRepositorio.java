package com.matricula.colegio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.matricula.colegio.entidad.Perfil;

@Repository
public interface IPerfilRepositorio extends JpaRepository<Perfil, Long>
{
	
}
