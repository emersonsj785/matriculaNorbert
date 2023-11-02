package com.matricula.colegio.servicio;

import com.matricula.colegio.entidad.FichaMatricula;
import com.matricula.colegio.entidad.dto.FichaMatriculaDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface IFichaMatriculaServicio
{	
	public FichaMatricula generarMatricula(FichaMatricula fichaMatricula);
	public JasperPrint generarInformePDF(FichaMatriculaDto fichaMatricula) throws JRException;
}
