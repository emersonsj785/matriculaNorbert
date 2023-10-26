package com.matricula.colegio.servicio;



import java.util.Optional;

import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.UsuarioDto;

public interface IUsuarioServicio
{
	public Usuario guardarUsuario(UsuarioDto registroDTO);
	
	Optional<Usuario> autenticarUsuario(String correo, String contrasenia);
	
	public Usuario obtnerUsuarioporId(Long ig);
	
	void eliminarUsuario(Long id);
}
