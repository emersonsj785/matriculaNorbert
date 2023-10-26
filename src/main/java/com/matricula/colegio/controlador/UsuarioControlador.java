package com.matricula.colegio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matricula.colegio.entidad.Usuario;
import com.matricula.colegio.entidad.dto.UsuarioDto;
import com.matricula.colegio.servicio.IUsuarioServicio;

@Controller
@RequestMapping("/registro")
public class UsuarioControlador
{
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@ModelAttribute("usuario")
	public UsuarioDto retornarNuevoUsuarioRegistro()
	{
		return new UsuarioDto();
	}
	
	@GetMapping
	public String mostrarFormularioRegistro()
	{
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioDto usuarioDto)
	{
		Usuario usuario = usuarioServicio.guardarUsuario(usuarioDto);
		return "redirect:/registroEmpleado?id_Usuario="+usuario.getId_Usuario();
	}
}
