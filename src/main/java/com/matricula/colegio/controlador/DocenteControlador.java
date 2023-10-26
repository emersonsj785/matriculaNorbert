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

import com.matricula.colegio.entidad.Docente;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.DocenteDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.servicio.IDocenteServicio;
import com.matricula.colegio.servicio.IUsuarioServicio;


@Controller
@RequestMapping("/docentes")
public class DocenteControlador
{
	@Autowired
	private IDocenteServicio docenteServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio; // Agrega el servicio de Rol
	
	@GetMapping("")
	public String listarDocentes(Model model) {
	    List<Docente> docentes = docenteServicio.obtenerTodosLosDocentes();
	    Map<Long, Usuario> usuarioMap = obtenerUsuariosMap(docentes);
	    model.addAttribute("docentes", docentes);
	    model.addAttribute("usuarioMap", usuarioMap);
	    return "docente"; // Retorna a docente.html
	}

	
	// Método para obtener un mapa de ID de Docente a Nombre de Docente
	private Map<Long, Usuario> obtenerUsuariosMap(List<Docente> docentes) {
	    Map<Long, Usuario> usuarioMap = new HashMap<>();
	    for (Docente docente : docentes) {
	        Long idUsuario = docente.getUsuario().getId_Usuario();
	        Usuario usuario = usuarioServicio.obtnerUsuarioporId(idUsuario);
	        usuarioMap.put(idUsuario, usuario);
	    }
	    return usuarioMap;
	}


    @GetMapping("/nuevo")
    public String mostrarFormularioDocente(Model model)
    {
        return "registroDocente";
    }
    
    @PostMapping("/registroDocente")
    public String registrarUsuarioYDocente(
            @ModelAttribute("usuario") UsuarioDto usuarioDto,
            @ModelAttribute("docente") DocenteDto docenteDto
    ) {
    	docenteServicio.registrarDocente(usuarioDto, docenteDto);
        
        return "redirect:/docentes/nuevo?exito"; // Puedes redirigir a una página de éxito o a donde sea necesario
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Docente> optionalDocente = docenteServicio.obtenerDocentePorId(id);
        
        if (optionalDocente.isPresent())
        {
            Docente docente = optionalDocente.get();
            model.addAttribute("docente", docente);
            return "editarDocente";
        } else
        {
            return "docenteNoEncontrado";
        }
    }    

    @PostMapping("/actualizar/{id}")
    public String actualizarDocente(@PathVariable Long id, 
        @ModelAttribute("docente") DocenteDto docenteDto) {
        
    	System.out.println("Controllador");
    	System.out.println(docenteDto);
        docenteServicio.actualizarDocente(id, docenteDto);

        return "redirect:/docentes";
    }

    @GetMapping("/{id}")
    public String eliminarDocente(@PathVariable Long id)
    {
    	Optional<Docente> optionalDocente = docenteServicio.obtenerDocentePorId(id);

        if (optionalDocente.isPresent()) {
            Docente docente = optionalDocente.get();
            Long idUsuario = docente.getUsuario().getId_Usuario();

            // Eliminar al docente por su ID
            docenteServicio.eliminarDocente(id);

            // Eliminar al usuario asociado por su ID
            usuarioServicio.eliminarUsuario(idUsuario);

            return "redirect:/docentes";
        } else {
            return "docenteNoEncontrado";
        }
    }
}
