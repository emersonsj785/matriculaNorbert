package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;

import com.matricula.colegio.entidad.Alumno;

public interface IAlumnoServicio
{
	List<Alumno> listarTodosAlumnos();
    
    Alumno guardarAlumno(Alumno alumno);
    
    Optional<Alumno> obtenerAlumnoPorId(Long id);
    
    Alumno actualizarAlumno(Long id, Alumno alumno);
    
    void eliminarAlumno(Long id);
    
    Optional<Alumno> autenticarAlumno(String correo, String contrasenia);
}
