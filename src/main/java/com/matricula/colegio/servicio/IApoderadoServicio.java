package com.matricula.colegio.servicio;
import java.util.List;
import java.util.Optional;

import com.matricula.colegio.entidad.Apoderado;
import com.matricula.colegio.entidad.dto.ApoderadoDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;

public interface IApoderadoServicio
{	
	void registrarApoderado(UsuarioDto usuarioDto, ApoderadoDto apoderadoDto);
	List<Apoderado> obtenerTodosLosApoderados();
    Optional<Apoderado> obtenerApoderadoPorId(Long id);
    void actualizarApoderado(Long id, ApoderadoDto apoderadoDto);
    void eliminarApoderado(Long id);
    
 // Nuevo método para buscar apoderado por DNI
    Optional<Apoderado> buscarApoderadoPorDNI(String dni);
}
