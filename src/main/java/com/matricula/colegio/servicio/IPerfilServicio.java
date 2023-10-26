package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;

import com.matricula.colegio.entidad.Perfil;

public interface IPerfilServicio
{
	List<Perfil> obtenerTodosLosPerfiles();
    Optional<Perfil> obtenerPerfilPorId(Long id);
    Perfil crearPerfil(Perfil perfil);
    Perfil actualizarPerfil(Long id, Perfil perfil);
    void eliminarPerfil(Long id);
}
