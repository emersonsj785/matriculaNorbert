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

import com.matricula.colegio.entidad.Curso;
import com.matricula.colegio.entidad.Grado;
import com.matricula.colegio.servicio.ICursoServicio;
import com.matricula.colegio.servicio.IGradoServicio;


@Controller
@RequestMapping("/cursos")
public class CursoControlador
{
	//Declaraciones
	//Para tabla maestra = Curso
	@Autowired
	private ICursoServicio cursoService;
	
	//Para tablas hijas 
	
	@Autowired
	private IGradoServicio gradoServicio;
	
	//Metodos de Controller
	@GetMapping("")
	public String listarCursos(Model modelo)
	{
		List<Curso> cursos = cursoService.listarTodosCursos();
		Map<Long, String> gradoMap = new HashMap<>();

		for (Curso curso : cursos) {
		    // Obtener el nombre de grado desde los objetos Curso
		    String nombreGrado = curso.getGrado().getNombre();
		    
		    // Agregar al mapa con ID como clave y nombre como valor
		    gradoMap.put(curso.getGrado().getId_Grado(), nombreGrado);
		}
		modelo.addAttribute("gradoMap", gradoMap);

		modelo.addAttribute("cursos", cursos);
		return "curso";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioGrados(Model modelo)
	{
		Curso curso = new Curso();
		List<Grado> grados = gradoServicio.listarTodosGrados(); // Obtener la lista de Grados
		modelo.addAttribute("grados", grados); // Agregar la lista de docentes al modelo
		modelo.addAttribute("curso", curso); //th object
		return "registroCurso";
	}
	
	@PostMapping("/registroCurso")
	public String registrarCursos(@ModelAttribute("curso") Curso curso) //ModelAtrribute es igual al del object
	{
		System.out.println(curso);
		cursoService.guardarCurso(curso);
		return "redirect:/cursos";
	}
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
        Optional<Curso> opcionCurso = cursoService.obtenerCursoPorId(id);
        
        if (opcionCurso.isPresent()) {
        	Curso curso = opcionCurso.get();
            List<Grado> grados = gradoServicio.listarTodosGrados(); // Obtener la lista de Grados
            modelo.addAttribute("grados", grados);
            modelo.addAttribute("curso", curso); //th object
            return "editarCurso";
        } else {
            return "cursoNoEncontrado"; // Ajusta el nombre de la vista según tus necesidades
        }
    }
	
	@PostMapping("/actualizar/{id}")
    public String actualizarCurso(@PathVariable Long id, 
            @ModelAttribute("curso") Curso curso) {
			cursoService.atualizarCurso(id, curso);
            return "redirect:/cursos";
        }
	
	@GetMapping("/{id}")
    public String eliminarCurso(@PathVariable Long id) {
		cursoService.eliminarCurso(id);
        return "redirect:/cursos";
    }
	
	
	
}
