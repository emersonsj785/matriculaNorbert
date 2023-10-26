package com.matricula.colegio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matricula.colegio.entidad.Grado;
import com.matricula.colegio.servicio.IGradoServicio;


@Controller
@RequestMapping("/grados")
public class GradoControlador
{
	@Autowired
	private IGradoServicio gradoServicio;
	
	@GetMapping({""})
	public String listarGrados(Model modelo)
	{
		modelo.addAttribute("grados",gradoServicio.listarTodosGrados());
		return "grado"; //Retorna a grado.html
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioGrados(Model modelo)
	{
		Grado grados = new Grado();
		modelo.addAttribute("grado", grados); //th object
		return "registroGrado";
	}
	
	@PostMapping("/registroGrado")
	public String registrarGrados(@ModelAttribute("grado") Grado grado) //ModelAtrribute es igual al del object
	{
		gradoServicio.guardarGrado(grado);
		return "redirect:/grados";
	}
	
	//Para Actualizar
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model modelo)
	{
		modelo.addAttribute("grado", gradoServicio.obtenerGrado(id)); //th object
		return "editarGrado";
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizarGrado(@PathVariable Long id, @ModelAttribute("grado") Grado grado, Model modelo)
	{
		Grado gradoexistente = gradoServicio.obtenerGrado(id);
		gradoexistente.setNombre(grado.getNombre());
		gradoexistente.setDescripcion(grado.getDescripcion());
		
		gradoServicio.actualizarGrado(gradoexistente);
		return "redirect:/grados";
	}
	
	//eliminar
	
	@GetMapping("/{id}")
	public String eliminarGrado(@PathVariable Long id)
	{
		gradoServicio.eliminarGrado(id);
		return "redirect:/grados";
	}
}
