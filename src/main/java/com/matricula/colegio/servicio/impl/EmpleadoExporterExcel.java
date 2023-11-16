package com.matricula.colegio.servicio.impl;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.matricula.colegio.entidad.Empleado;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class EmpleadoExporterExcel
{
	
	private XSSFWorkbook libro;
	private XSSFSheet hoja;
	
	private List <Empleado> listaEmpleados;

	public EmpleadoExporterExcel(List<Empleado> listaEmpleados)
	{
		this.listaEmpleados = listaEmpleados;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Empleados");
		
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
	    
	    Cell celdaTitulo = tituloFila.createCell(1);
	    celdaTitulo.setCellValue("Tabla de Empleados");
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
	    celda.setCellValue("ID Empleado");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(2);
	    celda.setCellValue("Puesto");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(3);
	    celda.setCellValue("Fecha Contrato");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(4);
	    celda.setCellValue("Horario Laboral");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(5);
	    celda.setCellValue("Salario");
	    celda.setCellStyle(estiloCabecera);
	}

	private void escribirDatosDeLaTabla() {
	    int numeroFilas = 4; // Comenzar desde la fila 5 (B5)
	    
	    CellStyle estiloDatos = libro.createCellStyle();
	    XSSFFont fuenteDatos = libro.createFont();
	    fuenteDatos.setFontHeight(14);
	    estiloDatos.setFont(fuenteDatos);
	    
	    // Definir bordes para las celdas de datos
	    estiloDatos.setBorderBottom(BorderStyle.THIN);
	    estiloDatos.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    estiloDatos.setBorderTop(BorderStyle.THIN);
	    estiloDatos.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    estiloDatos.setBorderLeft(BorderStyle.THIN);
	    estiloDatos.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    estiloDatos.setBorderRight(BorderStyle.THIN);
	    estiloDatos.setRightBorderColor(IndexedColors.BLACK.getIndex());

	    for (Empleado empleado : listaEmpleados) {
	        Row fila = hoja.createRow(numeroFilas++);
	        
	        for (int i = 1; i < 6; i++) { // Comenzar desde B hasta F (columnas 2 a 6)
	            Cell celda = fila.createCell(i);
	            celda.setCellStyle(estiloDatos);
	            
	            switch (i) {
	                case 1:
	                    celda.setCellValue(empleado.getId_Empleado());
	                    break;
	                case 2:
	                    celda.setCellValue(empleado.getPuesto());
	                    break;
	                case 3:
	                    celda.setCellValue(empleado.getFecha_Contratacion());
	                    break;
	                case 4:
	                    celda.setCellValue(empleado.getHorario_Trabajo());
	                    break;
	                case 5:
	                    celda.setCellValue(empleado.getSalario().toString());
	                    break;
	                default:
	                    break;
	            }
	            
	            hoja.autoSizeColumn(i); // Ajustar el ancho de la columna automáticamente
	        }
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
