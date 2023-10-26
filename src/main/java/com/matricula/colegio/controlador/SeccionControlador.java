package com.matricula.colegio.controlador;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matricula.colegio.entidad.Seccion;
import com.matricula.colegio.servicio.ISeccionServicio;




@Controller
@RequestMapping("/secciones")
public class SeccionControlador
{
	//Declaraciones
	
	@Autowired
	private ISeccionServicio seccionServicio;
	
	//Metodos de Controller
	@GetMapping("")
	public String listarSecciones(Model modelo)
	{
		List<Seccion> secciones = seccionServicio.listarTodosSeccion();
		modelo.addAttribute("secciones", secciones);
		return "seccion";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioSecciones(Model modelo)
	{
		Seccion seccion = new Seccion();
		modelo.addAttribute("seccion", seccion); //th object
		return "registroSeccion";
	}
	
	@PostMapping("/registroSeccion")
	public String registrarSeccion(@ModelAttribute("seccion") Seccion seccion) //ModelAtrribute es igual al del object
	{
		seccionServicio.guardarSeccion(seccion);
		return "redirect:/secciones";
	}
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
        Optional<Seccion> opcionSeccion = seccionServicio.obtenerSeccionPorId(id);
        
        if (opcionSeccion.isPresent())
        {
        	Seccion seccion = opcionSeccion.get();
            modelo.addAttribute("seccion", seccion); //th object
            return "editarSeccion";
        } else
        {
            return "seccionNoEncontrado"; // Ajusta el nombre de la vista según tus necesidades
        }
    }
	
	@PostMapping("/actualizar/{id}")
    public String actualizarSeccion(@PathVariable Long id, 
            @ModelAttribute("seccion") Seccion seccion)
		{
		seccionServicio.actualizarSeccion(id, seccion);
            return "redirect:/secciones";
        }
	
	@GetMapping("/{id}")
    public String eliminarSeccion(@PathVariable Long id)
	{
		seccionServicio.eliminarSeccion(id);
        return "redirect:/secciones";
    }
		
	
	
}
