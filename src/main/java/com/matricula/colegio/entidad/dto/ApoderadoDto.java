package com.matricula.colegio.entidad.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApoderadoDto
{
	private UsuarioDto usuario;
	private String parentesco;
    private String ocupacion;
    private String direccion_Residencia;
    
    
    
}