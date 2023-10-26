package com.matricula.colegio.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Empleado;
import com.matricula.colegio.entidad.Perfil;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.EmpleadoDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.repositorio.IEmpleadoRepositorio;
import com.matricula.colegio.repositorio.IPerfilRepositorio;
import com.matricula.colegio.repositorio.IUsuarioRepositorio;
import com.matricula.colegio.servicio.IEmpleadoServicio;

import jakarta.transaction.Transactional;

@Service
public class EmpleadoServicioImpl implements IEmpleadoServicio
{
	@Autowired
    private IEmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IPerfilRepositorio perfilRespositorio;

    @Override
    public void registrarEmpleado(UsuarioDto usuarioDto, EmpleadoDto empleadoDto) {
        Long perfilId = 2L; // Asegúrate de usar el ID correcto del perfil que deseas asignar

        // Recuperar el perfil existente
        Perfil perfilExistente = perfilRespositorio.findById(perfilId).orElse(null);

        // Crear un objeto Usuario y configurar sus datos
        Usuario usuario = new Usuario();
        usuario.setDni(usuarioDto.getDni());
        usuario.setNombres(usuarioDto.getNombres());
        usuario.setApellidos(usuarioDto.getApellidos());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setFecha_Nacimiento(usuarioDto.getFecha_Nacimiento());
        usuario.setSexo(usuarioDto.getSexo());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setContrasenia(usuarioDto.getContrasenia());
        usuario.setPerfil(perfilExistente);

        usuario = usuarioRepositorio.save(usuario);

        // Crear un objeto de Empleado y configurar sus datos
        Empleado empleado = new Empleado();
        empleado.setUsuario(usuario);
        empleado.setPuesto(empleadoDto.getPuesto());
        empleado.setFecha_Contratacion(empleadoDto.getFecha_Contratacion());
        empleado.setHorario_Trabajo(empleadoDto.getHorario_Trabajo());
        empleado.setSalario(empleadoDto.getSalario());

        empleadoRepositorio.save(empleado);
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleadoRepositorio.findById(id);
    }

    @Transactional
    @Override
    public void actualizarEmpleado(Long id, EmpleadoDto empleadoDto) {
        Optional<Empleado> optionalEmpleado = empleadoRepositorio.findById(id);

        if (optionalEmpleado.isPresent()) {
            Empleado empleadoExistente = optionalEmpleado.get();

            // Actualizar los campos del Empleado
            empleadoExistente.setPuesto(empleadoDto.getPuesto());
            empleadoExistente.setFecha_Contratacion(empleadoDto.getFecha_Contratacion());
            empleadoExistente.setHorario_Trabajo(empleadoDto.getHorario_Trabajo());
            empleadoExistente.setSalario(empleadoDto.getSalario());

            // Obtener el usuario asociado al empleado
            Usuario usuario = empleadoExistente.getUsuario();

            // Actualizar los campos relacionados del Usuario
            usuario.setNombres(empleadoDto.getUsuario().getNombres());
            usuario.setApellidos(empleadoDto.getUsuario().getApellidos());
            usuario.setDni(empleadoDto.getUsuario().getDni());
            usuario.setTelefono(empleadoDto.getUsuario().getTelefono());
            usuario.setCorreo(empleadoDto.getUsuario().getCorreo());
            usuario.setFecha_Nacimiento(empleadoDto.getUsuario().getFecha_Nacimiento());
            usuario.setSexo(empleadoDto.getUsuario().getSexo());

            // Puedes agregar más campos de usuario si es necesario

            // Guardar los cambios en la base de datos
            empleadoRepositorio.save(empleadoExistente);
        } else {
            throw new RuntimeException("No se encontró Empleado con el ID proporcionado");
        }
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepositorio.deleteById(id);
    }
	
}
