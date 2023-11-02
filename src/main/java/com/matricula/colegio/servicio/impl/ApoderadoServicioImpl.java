package com.matricula.colegio.servicio.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matricula.colegio.entidad.Apoderado;
import com.matricula.colegio.entidad.Perfil;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.ApoderadoDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.repositorio.IApoderadoRepositorio;
import com.matricula.colegio.repositorio.IPerfilRepositorio;
import com.matricula.colegio.repositorio.IUsuarioRepositorio;
import com.matricula.colegio.servicio.IApoderadoServicio;

@Service
public class ApoderadoServicioImpl implements IApoderadoServicio
{

	
	@Autowired 
	private IApoderadoRepositorio apoderadoRepositorio;
	
	@Autowired
	private IUsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private IPerfilRepositorio perfilRespositorio;

	@Override
	@Transactional
	public void registrarApoderado(UsuarioDto usuarioDto, ApoderadoDto apoderadoDto)
	{
		Long perfilId = 4L;
		
		Perfil perfilExistente = perfilRespositorio.findById(perfilId).orElse(null);
		
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
	    
	    //Apoderado
	    Apoderado apoderado = new Apoderado();
	    apoderado.setUsuario(usuario);
	    apoderado.setParentesco(apoderadoDto.getParentesco());
	    apoderado.setOcupacion(apoderadoDto.getOcupacion());
	    apoderado.setDireccion_Residencia(apoderadoDto.getDireccion_Residencia());
	    
	    apoderadoRepositorio.save(apoderado);
	}

	@Override
	public List<Apoderado> obtenerTodosLosApoderados()
	{
		return apoderadoRepositorio.findAll();
	}

	@Override
	public Optional<Apoderado> obtenerApoderadoPorId(Long id)
	{
		return apoderadoRepositorio.findById(id);
	}

	@Override
	@Transactional
	public void actualizarApoderado(Long id, ApoderadoDto apoderadoDto)
	{
		Optional<Apoderado> optionalApoderado = apoderadoRepositorio.findById(id);
		
		if(optionalApoderado.isPresent())
		{
			Apoderado apoderadoExistente = optionalApoderado.get();
			
			//Actualizado
			apoderadoExistente.setParentesco(apoderadoDto.getParentesco());
			apoderadoExistente.setOcupacion(apoderadoDto.getOcupacion());
			apoderadoExistente.setDireccion_Residencia(apoderadoDto.getDireccion_Residencia());
			
			//Obtener usuario
			Usuario usuario = apoderadoExistente.getUsuario();
			
			//Actualizar
			usuario.setNombres(apoderadoDto.getUsuario().getNombres());
            usuario.setApellidos(apoderadoDto.getUsuario().getApellidos());
            usuario.setDni(apoderadoDto.getUsuario().getDni());
            usuario.setTelefono(apoderadoDto.getUsuario().getTelefono());
            usuario.setCorreo(apoderadoDto.getUsuario().getCorreo());
            usuario.setFecha_Nacimiento(apoderadoDto.getUsuario().getFecha_Nacimiento());
            usuario.setSexo(apoderadoDto.getUsuario().getSexo());
            
            apoderadoRepositorio.save(apoderadoExistente);
		}else
		{
			throw new RuntimeException("No se encontró Docente con el ID proporcionado");
		}
	}

	@Override
	public void eliminarApoderado(Long id)
	{
		apoderadoRepositorio.deleteById(id);
	}

	@Override
    public Optional<Apoderado> buscarApoderadoPorDNI(String dni) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByDni(dni);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // Ahora, busca el apoderado relacionado con este usuario
            return apoderadoRepositorio.findByUsuario(usuario);
        }
        return Optional.empty(); // Retornar un Optional vacío si no se encuentra el apoderado
    }
	
	
}
