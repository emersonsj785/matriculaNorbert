package com.matricula.colegio.servicio.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matricula.colegio.entidad.FichaMatricula;
import com.matricula.colegio.repositorio.IFichaMatriculaRepositorio;
import com.matricula.colegio.servicio.IFichaMatriculaServicio;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class FichaMatriculaServicioImpl implements  IFichaMatriculaServicio
{

	@Autowired 
	private IFichaMatriculaRepositorio fichaMatriculaRepositorio;

	@Override
	public FichaMatricula generarMatricula(FichaMatricula fichaMatricula)
	{
		return fichaMatriculaRepositorio.save(fichaMatricula);
	}
	
	public JasperPrint generarInformePDF(FichaMatricula fichaMatricula) throws JRException{
            // Ruta al archivo .jrxml que define el diseño del informe
            String reportFile = "/Ficha_Matricula.jrxml";
            String rutaLogo = "/static/images/logoImagen.png";
            // Cargar el archivo .jrxml
            InputStream inputStream = getClass().getResourceAsStream(reportFile);
            InputStream logo = getClass().getResourceAsStream(rutaLogo);

            // Compilar el archivo .jrxml a un objeto JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // Crear un mapa de parámetros para el informe (si es necesario)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("LogoImagen", logo);
            // Crear una lista de objetos si el informe requiere datos
            List<FichaMatricula> data = List.of(fichaMatricula);

            // Crear una fuente de datos con la lista
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

            // Llenar el informe con los datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return jasperPrint;
    }

    

}
