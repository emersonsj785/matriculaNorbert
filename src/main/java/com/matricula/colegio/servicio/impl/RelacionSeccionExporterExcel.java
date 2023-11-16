package com.matricula.colegio.servicio.impl;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.matricula.colegio.entidad.DocenteCurso;
import com.matricula.colegio.entidad.DocenteCursoSeccion;
import com.matricula.colegio.entidad.Empleado;
import com.matricula.colegio.entidad.Seccion;
import com.matricula.colegio.entidad.Usuario;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class RelacionSeccionExporterExcel
{
	
	private XSSFWorkbook libro;
	private XSSFSheet hoja;
	
	private List <DocenteCursoSeccion> listaRelacion;

	public RelacionSeccionExporterExcel(List<DocenteCursoSeccion> listaRelacion)
	{
		this.listaRelacion = listaRelacion;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Relación");
		
	}

	private void escribirTitulo() {
	    Row tituloFila = hoja.createRow(1); // Fila para el título
	    
	    CellStyle estiloTitulo = libro.createCellStyle();
	    XSSFFont fuenteTitulo = libro.createFont();
	    fuenteTitulo.setBold(true);
	    fuenteTitulo.setFontHeight(18);
	    estiloTitulo.setFont(fuenteTitulo);
	    
	    // Fusionar celdas para el título
	    hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 4)); // Fusionar B2:F2
	    
	    estiloTitulo.setAlignment(HorizontalAlignment.CENTER); // Centrar horizontalmente el contenido
	    
	    Cell celdaTitulo = tituloFila.createCell(1);
	    celdaTitulo.setCellValue("Lista de Relación");
	    celdaTitulo.setCellStyle(estiloTitulo);
	}

	private void escribirCabeceraDeTabla() {
	    Row filaCabecera = hoja.createRow(3); // Comienza desde la fila 4 (B4)
	    
	    CellStyle estiloCabecera = libro.createCellStyle();
	    XSSFFont fuenteCabecera = libro.createFont();
	    fuenteCabecera.setBold(true);
	    fuenteCabecera.setFontHeight(16);
	    estiloCabecera.setFont(fuenteCabecera);
	    
	    // Color celeste para las celdas de cabecera
	    estiloCabecera.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	    estiloCabecera.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    
	    // Definir bordes para las celdas de cabecera
	    estiloCabecera.setBorderBottom(BorderStyle.THIN);
	    estiloCabecera.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    estiloCabecera.setBorderTop(BorderStyle.THIN);
	    estiloCabecera.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    estiloCabecera.setBorderLeft(BorderStyle.THIN);
	    estiloCabecera.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    estiloCabecera.setBorderRight(BorderStyle.THIN);
	    estiloCabecera.setRightBorderColor(IndexedColors.BLACK.getIndex());

	    // Crear celdas y aplicar estilo a las cabeceras
	    Cell celda = filaCabecera.createCell(1);
	    celda.setCellValue("Nombre Docente");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(2);
	    celda.setCellValue("Nombre Curso");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(3);
	    celda.setCellValue("Sección");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(4);
	    celda.setCellValue("Aforo");
	    celda.setCellStyle(estiloCabecera);
	    
	}

	private void escribirDatosDeLaTabla() {
	    int numeroFilas = 4; // Comenzar desde la fila 5 (B5)

	    CellStyle estiloDatos = libro.createCellStyle();
	    XSSFFont fuenteDatos = libro.createFont();
	    fuenteDatos.setFontHeight(14);
	    estiloDatos.setFont(fuenteDatos);

	    estiloDatos.setBorderBottom(BorderStyle.THIN);
	    estiloDatos.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    estiloDatos.setBorderTop(BorderStyle.THIN);
	    estiloDatos.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    estiloDatos.setBorderLeft(BorderStyle.THIN);
	    estiloDatos.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    estiloDatos.setBorderRight(BorderStyle.THIN);
	    estiloDatos.setRightBorderColor(IndexedColors.BLACK.getIndex());

	    for (DocenteCursoSeccion relacion : listaRelacion) {
	    	DocenteCurso docenteCurso = relacion.getDocenteCurso();
	    	Seccion seccion = relacion.getSeccion();
	    	
	    	String nombreDocente = relacion.getDocenteCurso().getDocente().getUsuario().getNombres();
        	String nombreCurso = relacion.getDocenteCurso().getCurso().getNombre();
        	//String Concatenado = " ("+nombreDocente + " - "+ nombreCurso +")";

	        Row fila = hoja.createRow(numeroFilas++);
	        
	        Cell celdaRelacionDocenteCurso = fila.createCell(1);
	        celdaRelacionDocenteCurso.setCellValue(nombreDocente);
	        celdaRelacionDocenteCurso.setCellStyle(estiloDatos);
	        
	        Cell celdaRelacionDocenteCursoCurso = fila.createCell(2);
	        celdaRelacionDocenteCursoCurso.setCellValue(nombreCurso);
	        celdaRelacionDocenteCursoCurso.setCellStyle(estiloDatos);
	        
	        Cell celdaRelacionSeccion = fila.createCell(3);
	        celdaRelacionSeccion.setCellValue(seccion.getId_Seccion()+ " - " + seccion.getNombre());
	        celdaRelacionSeccion.setCellStyle(estiloDatos);
	        
	        Cell celdaRelacionSeccionAforo = fila.createCell(4);
	        celdaRelacionSeccionAforo.setCellValue(seccion.getAforo());
	        celdaRelacionSeccionAforo.setCellStyle(estiloDatos);

	    }

	    // Autoajustar el ancho de las columnas después de insertar los datos
	    for (int i = 1; i <= 5; i++) { // Comenzar desde la columna 1 hasta la 8
	        hoja.autoSizeColumn(i);
	    }
	}

	
	public void exportar(HttpServletResponse response) throws IOException
	{
		escribirTitulo();
		escribirCabeceraDeTabla();
		escribirDatosDeLaTabla();
		
		ServletOutputStream outputStream = response.getOutputStream();
		libro.write(outputStream);
		
		libro.close();
		outputStream.close();
	}
	
	
	
}
