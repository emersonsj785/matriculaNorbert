package com.matricula.colegio.servicio.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matricula.colegio.entidad.Docente;
import com.matricula.colegio.entidad.Perfil;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.DocenteDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.repositorio.IDocenteRepositorio;
import com.matricula.colegio.repositorio.IPerfilRepositorio;
import com.matricula.colegio.repositorio.IUsuarioRepositorio;
import com.matricula.colegio.servicio.IDocenteServicio;

@Service
public class DocenteServicioImpl implements IDocenteServicio
{

	
	@Autowired 
	private IDocenteRepositorio docenteRepositorio;
	
	@Autowired
	private IUsuarioRepositorio usuarioRepositorio;
	
	@Autowired 
	private IPerfilRepositorio perfilRespositorio;

	@Override
	public void registrarDocente(UsuarioDto usuarioDto, DocenteDto docenteDto)
	{
		Long perfilId = 3L; // Aquí debes usar el ID correcto del perfil que deseas asignar

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
	    
	    //Crear un objeto de Docente y configurar sus datos
	    Docente docente = new Docente();
	    docente.setUsuario(usuario);
	    docente.setEspecializacion(docenteDto.getEspecializacion());
	    docente.setNivel_Educativo(docenteDto.getNivel_Educativo());
	    docente.setTitulo_Academico(docenteDto.getTitulo_Academico());
	    
	    docenteRepositorio.save(docente);
		
	}

	@Override
	public List<Docente> obtenerTodosLosDocentes()
	{
		return docenteRepositorio.findAll();
	}

	@Override
	public Optional<Docente> obtenerDocentePorId(Long id)
	{
		return docenteRepositorio.findById(id);
	}

	@Override
	@Transactional
	public void actualizarDocente(Long id, DocenteDto docenteDto) {
		Optional<Docente> optionalDocente = docenteRepositorio.findById(id);

        if (optionalDocente.isPresent()) {
            Docente docenteExistente = optionalDocente.get();

            // Actualizar los campos del Docente
            docenteExistente.setEspecializacion(docenteDto.getEspecializacion());
            docenteExistente.setNivel_Educativo(docenteDto.getNivel_Educativo());
            docenteExistente.setTitulo_Academico(docenteDto.getTitulo_Academico());

            // Obtener el usuario asociado al docente
            Usuario usuario = docenteExistente.getUsuario();
            
            // Actualizar los campos relacionados del Usuario
            usuario.setNombres(docenteDto.getUsuario().getNombres());
            usuario.setApellidos(docenteDto.getUsuario().getApellidos());
            usuario.setDni(docenteDto.getUsuario().getDni());
            usuario.setTelefono(docenteDto.getUsuario().getTelefono());
            usuario.setCorreo(docenteDto.getUsuario().getCorreo());
            usuario.setFecha_Nacimiento(docenteDto.getUsuario().getFecha_Nacimiento());
            usuario.setSexo(docenteDto.getUsuario().getSexo());
            
            // Puedes agregar más campos de usuario si es necesario

            // Guardar los cambios en la base de datos
            docenteRepositorio.save(docenteExistente);
        } else {
            throw new RuntimeException("No se encontró Docente con el ID proporcionado");
        }
	}

	@Override
	public void eliminarDocente(Long id)
	{
		docenteRepositorio.deleteById(id);
		
	}

	@Override
	public Docente obtenerDocenteIdCurso(Long id)
	{
		return docenteRepositorio.findById(id).get();
	}
	
	
}
