package com.matricula.colegio.controlador;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matricula.colegio.entidad.Alumno;
import com.matricula.colegio.entidad.Apoderado;
import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.entidad.DocenteCursoSeccion;
import com.matricula.colegio.entidad.FichaMatricula;
import com.matricula.colegio.entidad.Seccion;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.ApoderadoDto;
import com.matricula.colegio.entidad.dto.FichaMatriculaDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.servicio.IAlumnoServicio;
import com.matricula.colegio.servicio.IApoderadoServicio;
import com.matricula.colegio.servicio.IDocenteCursoSeccionServicio;
import com.matricula.colegio.servicio.IDocenteCursoServicio;
import com.matricula.colegio.servicio.IFichaMatriculaServicio;
import com.matricula.colegio.servicio.ISeccionServicio;
import com.matricula.colegio.servicio.IUsuarioServicio;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


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
	 
	 @Autowired
	 private IApoderadoServicio apoderadoServicio;
	 
	 @Autowired
	 private IFichaMatriculaServicio fichaMatriculaServicio;
	 
	 //VARIABLES
	 public Long ID_ALUMNO= 0L;
	 public String NOMBRE_ALUMNO= "";
	 public Long ID_APODERADO = 0L;


    @GetMapping({"/login","/"})
    public String mostrarFormularioLogin() {
        return "login"; // Asegúrate de crear la vista login.html
    }

    
    @PostMapping("/login")
    public String autenticarUsuario(String correo, String contrasenia, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOptional = usuarioServicio.autenticarUsuario(correo, contrasenia);

        if (!usuarioOptional.isPresent()) {
            Optional<Alumno> alumnoOptional = alumnoServicio.autenticarAlumno(correo, contrasenia);

            if (alumnoOptional.isPresent()) {
            	Alumno alumno = alumnoOptional.get();
            	ID_ALUMNO = alumno.getId_Alumno();
            	NOMBRE_ALUMNO = alumno.getNombres();
            	//redirectAttributes.addFlashAttribute("nombres", alumno.getNombres());
            	model.addAttribute("nombres", alumno.getNombres());
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
        model.addAttribute("nombres", NOMBRE_ALUMNO);
        model.addAttribute("ID_ALUMNO", ID_ALUMNO);
        model.addAttribute("apoderadoId", ID_APODERADO);


        return "alumnoDashboard";
    }
    
    @PostMapping("/buscarApoderado")
    public String buscarApoderado(@RequestParam("dni") String dni, Model model) {
        Optional<Apoderado> apoderadoOptional = apoderadoServicio.buscarApoderadoPorDNI(dni);

        if (apoderadoOptional.isPresent()) {
        	Apoderado apoderado = apoderadoOptional.get();
            model.addAttribute("exito", true);
            model.addAttribute("apoderadoId", apoderado.getId_Apoderado());
            ID_APODERADO = apoderado.getId_Apoderado();
            System.out.println("Estamos en exito");
        } else {
        	System.out.println("Estamos en falso");
        	return "registroApoderadoPersonalizado";
        }

        return mostrarAlumnoDashboard(model);
    }
    
    @PostMapping("/registroApoderadoPersonalizado")
    public String registroApoderadoPersonalizado(
            @ModelAttribute("usuario") UsuarioDto usuarioDto,
            @ModelAttribute("apoderado") ApoderadoDto apoderadoDto) {
        
        apoderadoServicio.registrarApoderado(usuarioDto, apoderadoDto);
        
        return "redirect:/dashboardAlumno";
    }
    
    @PostMapping("/registrarMatricula")
    public String registrarMatricula(
            @RequestParam("idSeccion") Long idSeccion,
            @RequestParam("idAlumno") Long idAlumno,
            @RequestParam("idApoderado") Long idApoderado,
            Model model, HttpServletResponse response) {
    	
        // Obtener la instancia de Seccion desde el servicio
        Optional<Seccion> seccionOptional = seccionServicio.obtenerSeccionPorId(idSeccion);
        Optional<Alumno> alumnoOptional = alumnoServicio.obtenerAlumnoPorId(idAlumno);
        Optional<Apoderado> apoderadoOptional = apoderadoServicio.obtenerApoderadoPorId(idApoderado);
        // Obtener la lista de DocenteCurso desde el servicio
        List<DocenteCurso> docenteCursos = docenteCursoServicio.listarTodosDocenteCursos();

        // Obtener la lista de DocenteCursoSeccion desde el servicio
        List<DocenteCursoSeccion> docenteCursoSeccionList = docenteCursoSeccionServicio.listarTodasDocenteCursoSeccion();
        
        	System.out.println("Estoy dentro");
        if (seccionOptional.isPresent()) {
            Seccion seccion = seccionOptional.get();
            
            if(alumnoOptional.isPresent())
            {
            	Alumno alumno = alumnoOptional.get();
            	
            		if(apoderadoOptional.isPresent())
            		{
            			Apoderado apoderado = apoderadoOptional.get();
            			System.out.println("Estamos dentro de la matricula");
            			FichaMatricula fichaMatricula = new FichaMatricula();
                        fichaMatricula.setSeccion(seccion);
                        fichaMatricula.setAlumno(alumno);
                        fichaMatricula.setApoderado(apoderado);
                        fichaMatricula.setPeriodo("2024 - I");
                        fichaMatricula.setFecha(new Date()); // Fecha actual
                        fichaMatricula.setEstado("Desactivado");

                        // Guardar la ficha de matrícula en la base de datos
                        fichaMatriculaServicio.generarMatricula(fichaMatricula);
                     // Actualizar el aforo restando 1
                        int aforoActual = seccion.getAforo();
                        if (aforoActual > 0) {
                            seccion.setAforo(aforoActual - 1);
                            seccionServicio.guardarSeccion(seccion);
                        }
                     // Generar el informe PDF y enviarlo como respuesta
                        try {
                        	//SETEO
                        	// Crear una lista para almacenar las cadenas formateadas
                        	List<String> docenteCursoStrings = new ArrayList<>();

                        	// Iterar a través de la lista de docenteCursoSeccionList
                        	for (DocenteCursoSeccion docenteCursoSeccion : docenteCursoSeccionList) {
                        	    // Verificar si la sección coincide
                        	    if (docenteCursoSeccion.getSeccion().getId_Seccion() == seccion.getId_Seccion()) {
                        	        // Construir la cadena con el formato deseado
                        	        String formattedString = docenteCursoSeccion.getId() + " [ " +
                        	                docenteCursoSeccion.getDocenteCurso().getDocente().getUsuario().getNombres() + " - " +
                        	                docenteCursoSeccion.getDocenteCurso().getCurso().getNombre() + " ] ";

                        	        // Agregar la cadena a la lista
                        	        docenteCursoStrings.add(formattedString);
                        	    }
                        	}

                        	// Convertir la lista de cadenas a una única cadena separada por comas
                        	String docenteCursoString = String.join(", ", docenteCursoStrings);

                        	// Establecer la cadena en el campo docenteCurso de FichaMatriculaDto
                        	//we.setDocenteCurso(docenteCursoString);

                        	//SETEO
                            // Generar el informe Jasper Report
                        	
                        	FichaMatriculaDto we =  new FichaMatriculaDto();
                        	we.setIdFicha(fichaMatricula.getIdFicha());
                        	we.setApoderado(fichaMatricula.getApoderado());
                        	we.setAlumno(fichaMatricula.getAlumno());
                        	we.setSeccion(fichaMatricula.getSeccion());
                        	we.setPeriodo(fichaMatricula.getPeriodo());
                        	we.setFecha(fichaMatricula.getFecha());
                        	we.setEstado(fichaMatricula.getEstado());
                        	//we.setDocenteCurso(docenteCursos);
                        	we.setDocenteCurso(docenteCursoString);
                        	we.setDocenteCursoSec(docenteCursoSeccionList);
                            JasperPrint jasperPrint = fichaMatriculaServicio.generarInformePDF(we);
                            
                            // Configurar la respuesta HTTP para el informe PDF
                            response.setContentType("application/pdf");
                            response.setHeader("Content-Disposition", "inline; filename=FichaMatricula.pdf");

                            // Exportar el informe a PDF y escribirlo en la respuesta HTTP
                            List<JasperPrint> jprintlist = new ArrayList<>();
                    		jprintlist.add(jasperPrint);
                    		//jasperStream.close();
                            
                            OutputStream out = response.getOutputStream();
                            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                            
                            JRPdfExporter exporter = new JRPdfExporter();
                    		exporter.setExporterInput(SimpleExporterInput.getInstance(jprintlist));
                            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                            exporter.exportReport();
                            
                            // Cerrar el flujo de salida
                            out.flush();
                            out.close();
                            
                        } catch (Exception e) {
                            // Manejar cualquier error que ocurra al generar o enviar el informe PDF
                            e.printStackTrace();
                        }

                        model.addAttribute("fichaExitosa", true);

            		}else{
            			model.addAttribute("rechazoApoderado", true);
            		}
            	
            }else {
            	model.addAttribute("rechazoAlumno", true);
            }
            
        } else {
        	model.addAttribute("rechazoSeccion", true);
        }
       
        return mostrarAlumnoDashboard(model);
    }





}
