package com.matricula.colegio.servicio;

import java.util.List;
import java.util.Optional;
import com.matricula.colegio.entidad.Empleado;
import com.matricula.colegio.entidad.dto.EmpleadoDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;

public interface IEmpleadoServicio
{
	void registrarEmpleado(UsuarioDto usuarioDto, EmpleadoDto epleadoDto);
	List<Empleado> obtenerTodosLosEmpleados();
    Optional<Empleado> obtenerEmpleadoPorId(Long id);
    void actualizarEmpleado(Long id, EmpleadoDto epleadoDto);
    void eliminarEmpleado(Long id);
}
