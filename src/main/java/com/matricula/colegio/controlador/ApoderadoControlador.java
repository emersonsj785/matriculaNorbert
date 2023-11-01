package com.matricula.colegio.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matricula.colegio.entidad.Apoderado;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.ApoderadoDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.servicio.IApoderadoServicio;
import com.matricula.colegio.servicio.IUsuarioServicio;


@Controller
@RequestMapping("/apoderados")
public class ApoderadoControlador
{
	@Autowired
	private IApoderadoServicio apoderadoServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio; // Agrega el servicio de Rol
	
	@GetMapping("")
	public String listarApoderados(Model model) {
	    List<Apoderado> apoderados = apoderadoServicio.obtenerTodosLosApoderados();
	    Map<Long, Usuario> usuarioMap = obtenerUsuariosMap(apoderados);
	    model.addAttribute("apoderados", apoderados);
	    model.addAttribute("usuarioMap", usuarioMap);
	    return "apoderado"; // Retorna a apoderado.html
	}

	
	// Método para obtener un mapa de ID de Apoderado a Nombre de Apoderado
	private Map<Long, Usuario> obtenerUsuariosMap(List<Apoderado> apoderados) {
	    Map<Long, Usuario> usuarioMap = new HashMap<>();
	    for (Apoderado apoderado : apoderados) {
	        Long idUsuario = apoderado.getUsuario().getId_Usuario();
	        Usuario usuario = usuarioServicio.obtnerUsuarioporId(idUsuario);
	        usuarioMap.put(idUsuario, usuario);
	    }
	    return usuarioMap;
	}


    @GetMapping("/nuevo")
    public String mostrarFormularioApoderado(Model model)
    {
        return "registroApoderado";
    }
    
    @PostMapping("/registroApoderado")
    public String registrarUsuarioYApoderado(
            @ModelAttribute("usuario") UsuarioDto usuarioDto,
            @ModelAttribute("apoderado") ApoderadoDto apoderadoDto
    ) {
    	apoderadoServicio.registrarApoderado(usuarioDto, apoderadoDto);
        
        return "redirect:/apoderados/nuevo?exito"; // Puedes redirigir a una página de éxito o a donde sea necesario
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model)
    {
        Optional<Apoderado> optionalApoderado = apoderadoServicio.obtenerApoderadoPorId(id);
        
        if (optionalApoderado.isPresent())
        {
        	Apoderado apoderado = optionalApoderado.get();
            model.addAttribute("apoderado", apoderado);
            return "editarApoderado";
        } else
        {
            return "apoderadoNoEncontrado";
        }
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarApoderado(@PathVariable Long id, 
        @ModelAttribute("apoderado") ApoderadoDto apoderadoDto)
    {
    	apoderadoServicio.actualizarApoderado(id, apoderadoDto);

        return "redirect:/apoderados";
    }

    @GetMapping("/{id}")
    public String eliminarApoderado(@PathVariable Long id)
    {
    	Optional<Apoderado> optionalApoderado = apoderadoServicio.obtenerApoderadoPorId(id);

        if (optionalApoderado.isPresent()) {
        	Apoderado apoderado = optionalApoderado.get();
            Long idUsuario = apoderado.getUsuario().getId_Usuario();

            // Eliminar al docente por su ID
            apoderadoServicio.eliminarApoderado(id);

            // Eliminar al usuario asociado por su ID
            usuarioServicio.eliminarUsuario(idUsuario);

            return "redirect:/apoderados";
        } else {
            return "apoderadoNoEncontrado";
        }
    }
}
