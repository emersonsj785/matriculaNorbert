package com.matricula.colegio.controlador;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.matricula.colegio.entidad.DatosAlumno;
import com.matricula.colegio.servicio.IDatosAlumnoServicio;




@Controller
@RequestMapping("/datosAlumno")
public class DatosAlumnoControlador
{
	@Autowired
	private IDatosAlumnoServicio datosAlumnoServicio;
	
	@GetMapping({""})
	public String listarDatos(Model modelo)
	{
		List<DatosAlumno> datos = datosAlumnoServicio.listarTodosDatos();
	    
	    for (DatosAlumno dato : datos) {
	        if (dato.getFotoDNI() != null) {
	            byte[] fotoDNIBytes = dato.getFotoDNI();
	            String fotoDNIBase64 = Base64.getEncoder().encodeToString(fotoDNIBytes);
	            dato.setFotoDNIBase64(fotoDNIBase64);
	        }
	        if (dato.getFotoLibreta() != null) {
	            byte[] fotoLibretaBytes = dato.getFotoLibreta();
	            String fotoLibretaBase64 = Base64.getEncoder().encodeToString(fotoLibretaBytes);
	            dato.setFotoLibretaBase64(fotoLibretaBase64);
	        }   
	    }

	    modelo.addAttribute("datosAlumno", datos);
	    return "datoAlumno"; // Retorna a datoAlumno.html
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioDatos(Model modelo)
	{
		DatosAlumno datos = new DatosAlumno();
		modelo.addAttribute("dato", datos); //th object
		return "registroDatoAlumno";
	}
	
	@PostMapping("/registroDatoAlumno")
	public String registrarDatos(@ModelAttribute("dato") DatosAlumno dato, 
			@RequestParam("fotoDNIFile") MultipartFile fotoDNIFile, 
			@RequestParam("fotoLibretaFile") MultipartFile fotoLibretaFile) {
	    try {
	        if (!fotoDNIFile.isEmpty()) {
	            dato.setFotoDNI(fotoDNIFile.getBytes());
	        }
	        if (!fotoLibretaFile.isEmpty()) {
	            dato.setFotoLibreta(fotoLibretaFile.getBytes());
	        }
	        datosAlumnoServicio.guardarDatos(dato);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "redirect:/datosAlumno";
	}
	
	//Actualizar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
	    DatosAlumno dato = datosAlumnoServicio.obtenerDatos(id);

	    if (dato != null) {
	        modelo.addAttribute("dato", dato);
	        return "editarDatoAlumno"; // Retorna a editarDatoAlumno.html con los datos a editar
	    } else {
	        // Manejar el caso en el que no se encuentra el dato a editar (por ejemplo, mostrar un mensaje de error)
	        return "redirect:/datosAlumno"; // Redirigir a la lista de datos si no se encuentra el dato
	    }
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizarDatos(@PathVariable Long id, @ModelAttribute("dato") DatosAlumno dato, 
	        @RequestParam("fotoDNIFile") MultipartFile fotoDNIFile, 
	        @RequestParam("fotoLibretaFile") MultipartFile fotoLibretaFile) {
	    DatosAlumno datoExistente = datosAlumnoServicio.obtenerDatos(id);

	    if (datoExistente != null) {
	        try {
	            if (!fotoDNIFile.isEmpty()) {
	                datoExistente.setFotoDNI(fotoDNIFile.getBytes());
	            }
	            if (!fotoLibretaFile.isEmpty()) {
	                datoExistente.setFotoLibreta(fotoLibretaFile.getBytes());
	            }
	            datosAlumnoServicio.actualizarDatos(datoExistente);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        // Manejar el caso en el que no se encuentra el dato a actualizar (por ejemplo, mostrar un mensaje de error)
	    }

	    return "redirect:/datosAlumno";
	}
	
	//Eliminar
	@GetMapping("/{id}")
	public String eliminarDatos(@PathVariable Long id)
	{
		datosAlumnoServicio.eliminarDatos(id);
		return "redirect:/datosAlumno";
	}
	
	@GetMapping("/imagen/{id}/{campo}")
	public ResponseEntity<byte[]> mostrarImagen(@PathVariable Long id, @PathVariable String campo) {
	    DatosAlumno dato = datosAlumnoServicio.obtenerDatos(id);
	    byte[] imagenBytes = null;

	    if ("fotoDNI".equals(campo)) {
	        imagenBytes = dato.getFotoDNI();
	    } else if ("fotoLibreta".equals(campo)) {
	        imagenBytes = dato.getFotoLibreta();
	    }

	    if (imagenBytes != null) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG); // Cambia a MediaType.IMAGE_PNG si es PNG
	        return new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
