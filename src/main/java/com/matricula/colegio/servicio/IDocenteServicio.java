package com.matricula.colegio.servicio;
import java.util.List;
import java.util.Optional;

import com.matricula.colegio.entidad.Docente;
import com.matricula.colegio.entidad.dto.DocenteDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;

public interface IDocenteServicio
{	
	void registrarDocente(UsuarioDto usuarioDto, DocenteDto docenteDto);
	List<Docente> obtenerTodosLosDocentes();
    Optional<Docente> obtenerDocentePorId(Long id);
    void actualizarDocente(Long id, DocenteDto docenteDto);
    void eliminarDocente(Long id);
    
    //Metodo para Cursos
    public Docente obtenerDocenteIdCurso(Long id);
}
