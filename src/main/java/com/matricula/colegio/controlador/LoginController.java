package com.matricula.colegio.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matricula.colegio.entidad.Alumno;
import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.entidad.DocenteCursoSeccion;
import com.matricula.colegio.entidad.Seccion;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.servicio.IAlumnoServicio;
import com.matricula.colegio.servicio.IDocenteCursoSeccionServicio;
import com.matricula.colegio.servicio.IDocenteCursoServicio;
import com.matricula.colegio.servicio.ISeccionServicio;
import com.matricula.colegio.servicio.IUsuarioServicio;


@Controller
public class LoginController
{
	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ISeccionServicio seccionServicio;
	
	@Autowired
	private IAlumnoServicio alumnoServicio;
	
	@Autowired
    private IDocenteCursoServicio docenteCursoServicio;
	
	 @Autowired
	 private IDocenteCursoSeccionServicio docenteCursoSeccionServicio;


    @GetMapping({"/login","/"})
    public String mostrarFormularioLogin() {
        return "login"; // Asegúrate de crear la vista login.html
    }

    /*@PostMapping("/login")
    public String autenticarUsuario(String correo, String contrasenia, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = usuarioServicio.autenticarUsuario(correo, contrasenia);
        if (usuarioOptional.isPresent()) {
        	
            Usuario usuario = usuarioOptional.get();
            long idPerfil = usuario.getPerfil().getId_Perfil();

        	redirectAttributes.addFlashAttribute("nombres", usuario.getNombres());
            if (idPerfil == 1) {
                return "redirect:/dashboard"; // Redirige a dashboard.html
            } else if (idPerfil == 2) {
                return "redirect:/empleado"; // Redirige a empleado.html
            } else if (idPerfil == 8) {
                return "redirect:/apoderado"; // Redirige a apoderado.html
            } else {
                // El perfil no es 1, 2 ni 3, puedes manejarlo como desees
                // Por ejemplo, redirigir a una página de error
                return "redirect:/error";
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; // Volver al formulario de inicio de sesión con un mensaje de error
        }
    }*/
    
    @PostMapping("/login")
    public String autenticarUsuario(String correo, String contrasenia, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = usuarioServicio.autenticarUsuario(correo, contrasenia);

        if (!usuarioOptional.isPresent()) {
            Optional<Alumno> alumnoOptional = alumnoServicio.autenticarAlumno(correo, contrasenia);

            if (alumnoOptional.isPresent()) {
                Alumno alumno = alumnoOptional.get();
                // Realizar las acciones necesarias para el usuario alumno
                // Por ejemplo, establecer atributos en el modelo y redirigir a su página correspondiente.
                redirectAttributes.addFlashAttribute("nombres", alumno.getNombres());
                return "redirect:/dashboardAlumno";
            }
        } else {
            Usuario usuario = usuarioOptional.get();
            long idPerfil = usuario.getPerfil().getId_Perfil();

            redirectAttributes.addFlashAttribute("nombres", usuario.getNombres());
            if (idPerfil == 1) {
                return "redirect:/dashboard";
            } else if (idPerfil == 2) {
                return "redirect:/empleado";
            } else if (idPerfil == 8) {
                return "redirect:/apoderado";
            } else {
                return "redirect:/error";
            }
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }


    
    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard";
    }
    
    @GetMapping("/empleado")
    public String mostrarSecretario() {
        return "empleadoDashboard";
    }

    /*@GetMapping("/apoderado")
    public String mostrarApoderado(Model modelo)
    {
        return "apoderadoDashboard";
    }*/
    
    
 // En tu controlador
    @GetMapping("/dashboardAlumno")
    public String mostrarAlumnoDashboard(Model model) {
        // Obtener la lista de Seccion desde el servicio
        List<Seccion> secciones = seccionServicio.listarTodosSeccion();

        // Obtener la lista de DocenteCurso desde el servicio
        List<DocenteCurso> docenteCursos = docenteCursoServicio.listarTodosDocenteCursos();

        // Obtener la lista de DocenteCursoSeccion desde el servicio
        List<DocenteCursoSeccion> docenteCursoSeccionList = docenteCursoSeccionServicio.listarTodasDocenteCursoSeccion();

        // Agregar las listas al modelo para que estén disponibles en la vista
        model.addAttribute("secciones", secciones);
        model.addAttribute("docenteCursos", docenteCursos);
        model.addAttribute("docenteCursoSeccionList", docenteCursoSeccionList);

        return "alumnoDashboard";
    }

}
