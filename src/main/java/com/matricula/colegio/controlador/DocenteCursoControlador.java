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
import com.matricula.colegio.entidad.Docente;
import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.servicio.ICursoServicio;
import com.matricula.colegio.servicio.IDocenteCursoServicio;
import com.matricula.colegio.servicio.IDocenteServicio;


@Controller
@RequestMapping("/docentes-cursos")
public class DocenteCursoControlador
{
	//Declaraciones
	//Para tabla maestra = Curso
	@Autowired
	private IDocenteCursoServicio docenteCursoService;
	
	//Para tablas Relacionadas 
	
	@Autowired
	private IDocenteServicio docenteService;
	
	@Autowired
	private ICursoServicio cursoService;
	
	//Metodos de Controller
	 @GetMapping("")
	    public String listarDocenteCursos(Model modelo) {
	        List<DocenteCurso> docenteCursos = docenteCursoService.listarTodosDocenteCursos();
	        
	        Map<Long, String> docenteMap = new HashMap<>();
			Map<Long, String> cursoMap = new HashMap<>();

			for (DocenteCurso docenteCurso : docenteCursos) {
			    // Obtener el nombre del docente y grado desde los objetos Curso
			    String nombreDocente = docenteCurso.getDocente().getUsuario().getNombres();
			    String nombreCurso = docenteCurso.getCurso().getNombre();
			    
			    // Agregar al mapa con ID como clave y nombre como valor
			    docenteMap.put(docenteCurso.getDocente().getId_Docente(), nombreDocente);
			    cursoMap.put(docenteCurso.getCurso().getId_Curso(), nombreCurso);
			}

			modelo.addAttribute("docenteMap", docenteMap);
			modelo.addAttribute("cursoMap", cursoMap);
			
	        modelo.addAttribute("docenteCursos", docenteCursos);
	        return "docenteCurso"; // Ajusta el nombre de la vista según tus necesidades
	    }

	    @GetMapping("/nuevo")
	    public String mostrarFormularioNuevo(Model modelo) {
	        DocenteCurso docenteCurso = new DocenteCurso();
	        List<Docente> docentes = docenteService.obtenerTodosLosDocentes();
	        List<Curso> cursos = cursoService.listarTodosCursos();
	        modelo.addAttribute("docentes", docentes);
	        modelo.addAttribute("cursos", cursos);
	        modelo.addAttribute("docenteCurso", docenteCurso);
	        return "registroDocenteCurso"; // Ajusta el nombre de la vista según tus necesidades
	    }

	    @PostMapping("/registroDocenteCurso")
	    public String registrarDocenteCurso(@ModelAttribute("docenteCurso") DocenteCurso docenteCurso) {
	        docenteCursoService.guardarDocenteCurso(docenteCurso);
	        return "redirect:/docentes-cursos";
	    }

	    @GetMapping("/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
	        Optional<DocenteCurso> opcionDocenteCurso = docenteCursoService.obtenerDocenteCursoPorId(id);

	        if (opcionDocenteCurso.isPresent()) {
	            DocenteCurso docenteCurso = opcionDocenteCurso.get();
	            List<Docente> docentes = docenteService.obtenerTodosLosDocentes();
	            List<Curso> cursos = cursoService.listarTodosCursos();
	            modelo.addAttribute("docentes", docentes);
	            modelo.addAttribute("cursos", cursos);
	            modelo.addAttribute("docenteCurso", docenteCurso);
	            return "editarDocenteCurso"; // Ajusta el nombre de la vista según tus necesidades
	        } else {
	            return "docenteCursoNoEncontrado"; // Ajusta el nombre de la vista según tus necesidades
	        }
	    }

	    @PostMapping("/actualizar/{id}")
	    public String actualizarDocenteCurso(@PathVariable Long id, @ModelAttribute("docenteCurso") DocenteCurso docenteCurso) {
	        docenteCursoService.actualizarDocenteCurso(id, docenteCurso);
	        return "redirect:/docentes-cursos";
	    }

	    @GetMapping("/eliminar/{id}")
	    public String eliminarDocenteCurso(@PathVariable Long id) {
	        docenteCursoService.eliminarDocenteCurso(id);
	        return "redirect:/docentes-cursos";
	    }
	
	
}
