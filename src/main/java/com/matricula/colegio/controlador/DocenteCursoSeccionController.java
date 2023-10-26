package com.matricula.colegio.controlador;
import java.util.ArrayList;
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

import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.entidad.DocenteCursoSeccion;
import com.matricula.colegio.entidad.Seccion;
import com.matricula.colegio.servicio.IDocenteCursoSeccionServicio;
import com.matricula.colegio.servicio.IDocenteCursoServicio;
import com.matricula.colegio.servicio.ISeccionServicio;


@Controller
@RequestMapping("/docentecursoseccion")
public class DocenteCursoSeccionController
{
	 @Autowired
	 private IDocenteCursoSeccionServicio docenteCursoSeccionService;
	 
	 //Para la vista
	 @Autowired
	 private IDocenteCursoServicio docenteCursoService;
	 
	 @Autowired
	 private ISeccionServicio seccionService;
	 

	    @GetMapping("")
	    public String listarDocenteCursoSeccion(Model model)
	    {
	        List<DocenteCursoSeccion> docenteCursoSeccionList = docenteCursoSeccionService.listarTodasDocenteCursoSeccion();
	        
	     // Cargar los nombres de los docentes y secciones relacionados
	        List<String> nombresDocentes = new ArrayList<>();
	        List<String> nombresSecciones = new ArrayList<>();
	        
	        for (DocenteCursoSeccion docenteCursoSeccion : docenteCursoSeccionList) {
	        	
	        	String nombreDocente = docenteCursoSeccion.getDocenteCurso().getDocente().getUsuario().getNombres();
	        	String nombreCurso = docenteCursoSeccion.getDocenteCurso().getCurso().getNombre();
	        	String Concatenado = " ("+nombreDocente + " - "+ nombreCurso +")";
	            nombresDocentes.add(docenteCursoSeccion.getDocenteCurso().getId_DocenteCurso()+" - " + Concatenado);
	            nombresSecciones.add(docenteCursoSeccion.getSeccion().getId_Seccion() + " - " +docenteCursoSeccion.getSeccion().getNombre());
	        }
	        
	        model.addAttribute("docenteCursoSeccionList", docenteCursoSeccionList);
	        model.addAttribute("nombresDocentes", nombresDocentes);
	        model.addAttribute("nombresSecciones", nombresSecciones);
	        return "docenteCursoSeccion";
	    }

	    @GetMapping("/nuevo")
	    public String mostrarFormularioNuevo(Model model)
	    {
	        DocenteCursoSeccion docenteCursoSeccion = new DocenteCursoSeccion();
	        // Puedes cargar datos relacionados, como docentes y secciones, si es necesario
	        
	        List<DocenteCurso> docenteCursos = docenteCursoService.listarTodosDocenteCursos(); // Carga los DocenteCursos disponibles
	        List<Seccion> secciones = seccionService.listarTodosSeccion(); // Carga las Secciones disponibles
	        model.addAttribute("docenteCursos", docenteCursos);
	        model.addAttribute("secciones", secciones);
	        model.addAttribute("docenteCursoSeccion", docenteCursoSeccion);
	        return "registroDocenteCursoSeccion";
	    }

	    @PostMapping("/registro")
	    public String registrarDocenteCursoSeccion(@ModelAttribute("docenteCursoSeccion") DocenteCursoSeccion docenteCursoSeccion)
	    {
	        docenteCursoSeccionService.guardarDocenteCursoSeccion(docenteCursoSeccion);
	        return "redirect:/docentecursoseccion";
	    }

	    @GetMapping("/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable Long id, Model model)
	    {
	        Optional<DocenteCursoSeccion> optionalDocenteCursoSeccion = docenteCursoSeccionService.obtenerDocenteCursoSeccionPorId(id);
	        if (optionalDocenteCursoSeccion.isPresent())
	        {
	            DocenteCursoSeccion docenteCursoSeccion = optionalDocenteCursoSeccion.get();
	            // Puedes cargar datos relacionados, como docentes y secciones, si es necesario
	            
	            List<DocenteCurso> docenteCursos = docenteCursoService.listarTodosDocenteCursos(); // Carga los DocenteCursos disponibles
		        List<Seccion> secciones = seccionService.listarTodosSeccion(); // Carga las Secciones disponibles
		        model.addAttribute("docenteCursos", docenteCursos);
		        model.addAttribute("secciones", secciones);
	            model.addAttribute("docenteCursoSeccion", docenteCursoSeccion);
	            return "editarDocenteCursoSeccion";
	        } else
	        {
	            return "docenteCursoSeccionNoEncontrado"; // Ajusta el nombre de la vista según tus necesidades
	        }
	    }

	    @PostMapping("/actualizar/{id}")
	    public String actualizarDocenteCursoSeccion(@PathVariable Long id, @ModelAttribute("docenteCursoSeccion") DocenteCursoSeccion docenteCursoSeccion)
	    {
	        docenteCursoSeccionService.actualizarDocenteCursoSeccion(id, docenteCursoSeccion);
	        return "redirect:/docentecursoseccion";
	    }

	    @GetMapping("/eliminar/{id}")
	    public String eliminarDocenteCursoSeccion(@PathVariable Long id)
	    {
	        docenteCursoSeccionService.eliminarDocenteCursoSeccion(id);
	        return "redirect:/docentecursoseccion";
	    }
	
	
	
}
