package com.matricula.colegio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.matricula.colegio.entidad.Rol;
import com.matricula.colegio.servicio.IRolServicio;


@Controller
public class RolControlador
{
	@Autowired
	private IRolServicio rolServicio;
	
	@GetMapping({"/roles"})
	public String listarRoles(Model modelo)
	{
		modelo.addAttribute("roles",rolServicio.listarTodosRoles());
		return "rol"; //Retorna a rol.html
	}
	
	@GetMapping("/roles/nuevo")
	public String mostrarFormularioRoles(Model modelo)
	{
		Rol roles = new Rol();
		modelo.addAttribute("roles", roles); //th object
		return "crearRoles";
	}
	
	@PostMapping("/roles")
	public String guardarRoles(@ModelAttribute("rol") Rol rol) //ModelAtrribute es igual al del object
	{
		rolServicio.guardarRol(rol);
		return "redirect:/roles";
	}
	
	//Para Actualizar
	
	@GetMapping("/roles/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model modelo)
	{
		modelo.addAttribute("rol", rolServicio.obtenerRolPorId(id)); //th object
		return "editarRol";
	}
	
	@PostMapping("roles/{id}")
	public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute("rol") Rol rol, Model modelo)
	{
		Rol rolexistente = rolServicio.obtenerRolPorId(id);
		rolexistente.setNombre_Rol(rol.getNombre_Rol());
		rolexistente.setDescripcion(rol.getDescripcion());
		
		rolServicio.atualizarRol(rolexistente);
		return "redirect:/roles";
	}
	
	//eliminar
	
	@GetMapping("/roles/{id}")
	public String eliminarRol(@PathVariable Long id)
	{
		rolServicio.eliminarRol(id);
		return "redirect:/roles";
	}
}
