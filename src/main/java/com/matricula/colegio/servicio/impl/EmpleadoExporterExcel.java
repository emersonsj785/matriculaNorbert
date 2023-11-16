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

import com.matricula.colegio.entidad.Empleado;
import com.matricula.colegio.entidad.Usuario;

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
	    hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 12)); // Fusionar B2:F2
	    
	    estiloTitulo.setAlignment(HorizontalAlignment.CENTER); // Centrar horizontalmente el contenido
	    
	    Cell celdaTitulo = tituloFila.createCell(1);
	    celdaTitulo.setCellValue("Lista de Empleados");
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
	    celda.setCellValue("Nombre");
	    celda.setCellStyle(estiloCabecera);

	    celda = filaCabecera.createCell(3);
	    celda.setCellValue("Apellido");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(4);
	    celda.setCellValue("DNI");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(5);
	    celda.setCellValue("Teléfono");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(6);
	    celda.setCellValue("Correo");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(7);
	    celda.setCellValue("Fecha Nacimiento");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(8);
	    celda.setCellValue("Sexo");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(9);
	    celda.setCellValue("Puesto");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(10);
	    celda.setCellValue("Fecha Contrato");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(11);
	    celda.setCellValue("Horario Laboral");
	    celda.setCellStyle(estiloCabecera);
	    
	    celda = filaCabecera.createCell(12);
	    celda.setCellValue("Salario");
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

	    for (Empleado empleado : listaEmpleados) {
	        Usuario usuario = empleado.getUsuario(); // Obtener el usuario asociado al empleado

	        Row fila = hoja.createRow(numeroFilas++);

	        Cell celdaEmpleadoId = fila.createCell(1);
	        celdaEmpleadoId.setCellValue(empleado.getId_Empleado());
	        celdaEmpleadoId.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioNombres = fila.createCell(2);
	        celdaUsuarioNombres.setCellValue(usuario.getNombres());
	        celdaUsuarioNombres.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioApellido = fila.createCell(3);
	        celdaUsuarioApellido.setCellValue(usuario.getApellidos());
	        celdaUsuarioApellido.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioDNI = fila.createCell(4);
	        celdaUsuarioDNI.setCellValue(usuario.getDni());
	        celdaUsuarioDNI.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioTelefono = fila.createCell(5);
	        celdaUsuarioTelefono.setCellValue(usuario.getTelefono());
	        celdaUsuarioTelefono.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioCorreo = fila.createCell(6);
	        celdaUsuarioCorreo.setCellValue(usuario.getCorreo());
	        celdaUsuarioCorreo.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioNacimiento = fila.createCell(7);
	        celdaUsuarioNacimiento.setCellValue(usuario.getFecha_Nacimiento());
	        celdaUsuarioNacimiento.setCellStyle(estiloDatos);
	        
	        Cell celdaUsuarioSexo = fila.createCell(8);
	        celdaUsuarioSexo.setCellValue(usuario.getSexo());
	        celdaUsuarioSexo.setCellStyle(estiloDatos);

	        Cell celdaEmpleadoPuesto = fila.createCell(9);
	        celdaEmpleadoPuesto.setCellValue(empleado.getPuesto());
	        celdaEmpleadoPuesto.setCellStyle(estiloDatos);

	        Cell celdaEmpleadoFechaContrato = fila.createCell(10);
	        celdaEmpleadoFechaContrato.setCellValue(empleado.getFecha_Contratacion());
	        celdaEmpleadoFechaContrato.setCellStyle(estiloDatos);

	        Cell celdaEmpleadoHorario = fila.createCell(11);
	        celdaEmpleadoHorario.setCellValue(empleado.getHorario_Trabajo());
	        celdaEmpleadoHorario.setCellStyle(estiloDatos);

	        Cell celdaEmpleadoSalario = fila.createCell(12);
	        celdaEmpleadoSalario.setCellValue(empleado.getSalario().toString());
	        celdaEmpleadoSalario.setCellStyle(estiloDatos);
	    }

	    // Autoajustar el ancho de las columnas después de insertar los datos
	    for (int i = 1; i <= 12; i++) { // Comenzar desde la columna 1 hasta la 8
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
