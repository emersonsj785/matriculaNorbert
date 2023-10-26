package com.matricula.colegio.servicio.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matricula.colegio.entidad.Perfil;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.repositorio.IPerfilRepositorio;
import com.matricula.colegio.repositorio.IUsuarioRepositorio;
import com.matricula.colegio.servicio.IUsuarioServicio;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio
{
	
	@Autowired 
	private IUsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private IPerfilRepositorio perfilRespositorio;

	@Override
    public Usuario guardarUsuario(UsuarioDto registroDTO)
	{
		
		Long perfilId = 1L; // Aquí debes usar el ID correcto del perfil que deseas asignar

		// Recuperar el perfil existente
		Perfil perfilExistente = perfilRespositorio.findById(perfilId).orElse(null);
        // Crear una instancia de Usuario a partir de UsuarioDto
        Usuario usuario = new Usuario(
        	perfilExistente,
            registroDTO.getDni(),
            registroDTO.getApellidos(),
            registroDTO.getNombres(),
            registroDTO.getTelefono(),
            //passwordEncoder.encode(),
            registroDTO.getCorreo(),
            registroDTO.getContrasenia(),
            registroDTO.getFecha_Nacimiento(),
            registroDTO.getSexo()
        );

        // Guardar el usuario en la base de datos
        usuario = usuarioRepositorio.save(usuario);

        return usuario;
    }
	
	public Optional<Usuario> autenticarUsuario(String correo, String contrasenia) {
        // Buscar al usuario por su correo
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByCorreo(correo);
        
        // Verificar si el usuario existe y si la contraseña coincide
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (contrasenia.equals(usuario.getContrasenia())) {
                return Optional.of(usuario);
            }
        }
        
        // Si las credenciales no coinciden, retorna un Optional vacío
        return Optional.empty();
    }

	@Override
	public Usuario obtnerUsuarioporId(Long ig)
	{
		return usuarioRepositorio.findById(ig).get();
	}
	
	
	@Override
    public void eliminarUsuario(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(id);
        
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            
            // Elimina al usuario utilizando el repositorio
            usuarioRepositorio.delete(usuarioExistente);
        } else {
            throw new RuntimeException("No se encontró usuario con el ID proporcionado");
        }
    }

	
}
