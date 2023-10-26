package com.matricula.colegio.entidad.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocenteDto
{
	private UsuarioDto usuario;
	private String especializacion;
    private String nivel_Educativo;
    private String titulo_Academico;
    
    
    
}