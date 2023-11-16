package com.matricula.colegio.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;
import com.matricula.colegio.entidad.Empleado;
import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.EmpleadoDto;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.servicio.IEmpleadoServicio;
import com.matricula.colegio.servicio.IUsuarioServicio;
import com.matricula.colegio.servicio.impl.EmpleadoExporterExcel;

import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/empleados")
public class EmpleadoControlador
{
	@Autowired
    private IEmpleadoServicio empleadoServicio;

    @Autowired
    private IUsuarioServicio usuarioServicio; // Asegúrate de tener el servicio de Usuario inyectado

    @GetMapping("")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoServicio.obtenerTodosLosEmpleados();
        Map<Long, Usuario> usuarioMap = obtenerUsuariosMap(empleados);
        model.addAttribute("empleados", empleados);
        model.addAttribute("usuarioMap", usuarioMap);
        return "empleado"; // Retorna a empleado.html (ajusta el nombre de la vista según tu aplicación)
    }

    // Método para obtener un mapa de ID de Empleado a Nombre de Empleado
    private Map<Long, Usuario> obtenerUsuariosMap(List<Empleado> empleados) {
        Map<Long, Usuario> usuarioMap = new HashMap<>();
        for (Empleado empleado : empleados) {
            Long idUsuario = empleado.getUsuario().getId_Usuario();
            Usuario usuario = usuarioServicio.obtnerUsuarioporId(idUsuario);
            usuarioMap.put(idUsuario, usuario);
        }
        return usuarioMap;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioEmpleado(Model model) {
        return "registroEmpleado"; // Ajusta el nombre de la vista según tu aplicación
    }

    @PostMapping("/registroEmpleado")
    public String registrarUsuarioYEmpleado(
            @ModelAttribute("usuario") UsuarioDto usuarioDto,
            @ModelAttribute("empleado") EmpleadoDto empleadoDto,
            @RequestParam("horaInicio") String horaInicio, 
            @RequestParam("horaFin") String horaFin
    ) {
    	 // Parsear y procesar la hora de inicio y hora de finalización
        String horarioTrabajo = horaInicio + " a " + horaFin;

        // Asignar los valores al DTO de empleado
        empleadoDto.setHorario_Trabajo(horarioTrabajo);
        
        empleadoServicio.registrarEmpleado(usuarioDto, empleadoDto);

        return "redirect:/empleados/nuevo?exito"; // Puedes redirigir a una página de éxito o a donde sea necesario
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Empleado> optionalEmpleado = empleadoServicio.obtenerEmpleadoPorId(id);

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            model.addAttribute("empleado", empleado);
            return "editarEmpleado"; // Ajusta el nombre de la vista según tu aplicación
        } else {
            return "empleadoNoEncontrado"; // Ajusta el nombre de la vista según tu aplicación
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable Long id,
                                     @ModelAttribute("empleado") EmpleadoDto empleadoDto,
                                     @RequestParam("horaInicio") String horaInicio,
                                     @RequestParam("horaFin") String horaFin)
    {
    	// Parsear y procesar la hora de inicio y hora de finalización
        String horarioTrabajo = horaInicio + " a " + horaFin;
        
        // Establecer el valor actualizado en el objeto EmpleadoDto
        empleadoDto.setHorario_Trabajo(horarioTrabajo);
    	
    	empleadoServicio.actualizarEmpleado(id, empleadoDto);

        return "redirect:/empleados";
    }

    @GetMapping("/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        Optional<Empleado> optionalEmpleado = empleadoServicio.obtenerEmpleadoPorId(id);

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            Long idUsuario = empleado.getUsuario().getId_Usuario();

            // Eliminar al empleado por su ID
            empleadoServicio.eliminarEmpleado(id);

            // Eliminar al usuario asociado por su ID
            usuarioServicio.eliminarUsuario(idUsuario);

            return "redirect:/empleados";
        } else {
            return "empleadoNoEncontrado"; // Ajusta el nombre de la vista según tu aplicación
        }
    }
    
    @GetMapping("/exportarExcel")
    public void exportarListadoExcel(HttpServletResponse response) throws DocumentException, IOException
    {
    	response.setContentType("application/octet-stream");
    	
    	DateFormat dataFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	String fechaActual = dataFormatter.format(new Date());
    	
    	String cabecera = "Content-Disposition";
    	String valor = "attachment; filename=Empleados_"+fechaActual+".xlsx";
    	
    	response.setHeader(cabecera, valor);
    	
    	List<Empleado> empleados = empleadoServicio.obtenerTodosLosEmpleados();
    	
    	EmpleadoExporterExcel exporter = new EmpleadoExporterExcel(empleados);
    	exporter.exportar(response);
    }
}
