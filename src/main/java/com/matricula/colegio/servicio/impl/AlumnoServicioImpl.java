package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matricula.colegio.entidad.Alumno;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.repositorio.IAlumnoRepositorio;
import com.matricula.colegio.servicio.IAlumnoServicio;

@Service
public class AlumnoServicioImpl implements IAlumnoServicio
{

	@Autowired 
	private IAlumnoRepositorio alumnoRepositorio;

	@Override
    public List<Alumno> listarTodosAlumnos() {
        return alumnoRepositorio.findAll();
    }

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        return alumnoRepositorio.save(alumno);
    }

    @Override
    public Optional<Alumno> obtenerAlumnoPorId(Long id) {
        return alumnoRepositorio.findById(id);
    }

    @Override
    public Alumno actualizarAlumno(Long id, Alumno alumno) {
        // Realiza la lógica para actualizar el alumno si es necesario
        return alumnoRepositorio.save(alumno);
    }

    @Override
    public void eliminarAlumno(Long id) {
    	alumnoRepositorio.deleteById(id);
    }
	
    public Optional<Alumno> autenticarAlumno(String correo, String contrasenia) {
        // Buscar al usuario por su correo
        Optional<Alumno> alumnoOptional = alumnoRepositorio.findByCorreo(correo);
        
        // Verificar si el usuario existe y si la contraseña coincide
        if (alumnoOptional.isPresent()) {
        	Alumno alumno = alumnoOptional.get();
            if (contrasenia.equals(alumno.getContrasenia())) {
                return Optional.of(alumno);
            }
        }
        
        // Si las credenciales no coinciden, retorna un Optional vacío
        return Optional.empty();
    }

}
