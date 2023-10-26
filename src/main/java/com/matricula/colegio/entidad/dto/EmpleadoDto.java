package com.matricula.colegio.entidad.dto;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpleadoDto
{
	private UsuarioDto usuario;
	private String puesto;
    private String fecha_Contratacion;
    private String horario_Trabajo;
    private BigDecimal salario;
    
}