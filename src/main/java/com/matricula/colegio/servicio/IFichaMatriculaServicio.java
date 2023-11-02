package com.matricula.colegio.servicio;

import com.matricula.colegio.entidad.FichaMatricula;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface IFichaMatriculaServicio
{	
	public FichaMatricula generarMatricula(FichaMatricula fichaMatricula);
	public JasperPrint generarInformePDF(FichaMatricula fichaMatricula) throws JRException;
}
