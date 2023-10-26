package com.matricula.colegio.controlador;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.matricula.colegio.entidad.Alumno;
import com.matricula.colegio.entidad.DatosAlumno;
import com.matricula.colegio.servicio.IAlumnoServicio;
import com.matricula.colegio.servicio.IDatosAlumnoServicio;

@Controller
@RequestMapping("/registroProceso")
public class RegistroProcesoAlumnoControlador
{
	@Autowired
	private IDatosAlumnoServicio datosAlumnoServicio;
	
	@Autowired
	private IAlumnoServicio alumnoService;
	
	@GetMapping("/nuevo")
	public String mostrarFormularioDatos(Model modelo)
	{
		DatosAlumno datos = new DatosAlumno();
		modelo.addAttribute("dato", datos); //th object
		System.out.println("Estoy en el controller dedl UsuarioRegistro");
		return "registroDatoPrevio";
	}
	
	@PostMapping("/registro")
    public String registrarDatos(@ModelAttribute("dato") DatosAlumno dato,
                                @ModelAttribute("alumno") Alumno alumno,
                                @RequestParam("fotoDNIFile") MultipartFile fotoDNIFile,
                                @RequestParam("fotoLibretaFile") MultipartFile fotoLibretaFile) {
        try {
            if (!fotoDNIFile.isEmpty()) {
                dato.setFotoDNI(fotoDNIFile.getBytes());
            }
            if (!fotoLibretaFile.isEmpty()) {
                dato.setFotoLibreta(fotoLibretaFile.getBytes());
            }

            // Primero registra los datos del alumno
            DatosAlumno datosAlumnoRegistrado = datosAlumnoServicio.guardarDatos(dato);

            // Obtén el ID de los datos del alumno registrado
            int idDatosAlumno = datosAlumnoRegistrado.getIdDato();

            // Asigna el ID de los datos del alumno al alumno
            alumno.setDatosAlumno(datosAlumnoRegistrado);

            // Registra al alumno con el ID de los datos del alumno
            alumnoService.guardarAlumno(alumno);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }
	

}
