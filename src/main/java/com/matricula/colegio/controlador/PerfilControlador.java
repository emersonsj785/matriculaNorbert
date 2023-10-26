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

import com.matricula.colegio.entidad.Perfil;
import com.matricula.colegio.entidad.Rol;
import com.matricula.colegio.servicio.IPerfilServicio;
import com.matricula.colegio.servicio.IRolServicio;


@Controller
public class PerfilControlador
{
	@Autowired
	private IPerfilServicio perfilServicio;
	
	@Autowired
	private IRolServicio rolService; // Agrega el servicio de Rol
	
	@GetMapping("/perfiles")
    public String listarPerfiles(Model model) {
		List<Perfil> perfiles = perfilServicio.obtenerTodosLosPerfiles();
	    model.addAttribute("perfiles", perfiles);
	    model.addAttribute("idRolMap", obtenerIdRolMap(perfiles)); // Agregar mapa de ID de Rol a Nombre de Rol
	    return "perfil"; // Retorna a perfil.html
    }
	
	// Método para obtener un mapa de ID de Rol a Nombre de Rol
	private Map<Long, String> obtenerIdRolMap(List<Perfil> perfiles) {
	    Map<Long, String> idRolMap = new HashMap<>();
	    for (Perfil perfil : perfiles) {
	        Long idRol = perfil.getRol().getId_Rol();
	        String nombreRol = rolService.obtenerRolPorId(idRol).getNombre_Rol(); // Obtener el nombre del rol desde el servicio de Rol
	        idRolMap.put(idRol, nombreRol);
	    }
	    return idRolMap;
	}

    @GetMapping("/perfiles/nuevo")
    public String mostrarFormularioPerfiles(Model model) {
        Perfil perfil = new Perfil();
        List<Rol> roles = rolService.listarTodosRoles(); // Obtener la lista de roles
        model.addAttribute("roles", roles); // Agregar la lista de roles al modelo
        model.addAttribute("perfil", perfil); // th:object
        return "crearPerfil";
    }

    @PostMapping("/perfiles")
    public String guardarPerfil(@ModelAttribute("perfil") Perfil perfil) {
    	perfilServicio.crearPerfil(perfil);
        return "redirect:/perfiles";
    }

    @GetMapping("/perfiles/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Perfil> optionalPerfil = perfilServicio.obtenerPerfilPorId(id);
        
        if (optionalPerfil.isPresent()) {
            Perfil perfil = optionalPerfil.get();
            List<Rol> roles = rolService.listarTodosRoles(); // Obtener la lista de roles
            model.addAttribute("perfil", perfil);
            model.addAttribute("roles", roles); // Agregar la lista de roles al modelo
            return "editarPerfil";
        } else {
            // Manejar el caso en que el perfil no se encuentre
            // Puedes redirigir a una página de error o realizar alguna otra acción
            return "perfilNoEncontrado"; // Ajusta el nombre de la vista según tus necesidades
        }
    }


    @PostMapping("/perfiles/{id}")
    public String actualizarPerfil(@PathVariable Long id, @ModelAttribute("perfil") Perfil perfil) {
    	perfilServicio.actualizarPerfil(id, perfil);
        return "redirect:/perfiles";
    }

    @GetMapping("/perfiles/{id}")
    public String eliminarPerfil(@PathVariable Long id) {
    	perfilServicio.eliminarPerfil(id);
        return "redirect:/perfiles";
    }
}
