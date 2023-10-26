package com.matricula.colegio.entidad;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Empleado;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;


    private String puesto;
    private String fecha_Contratacion;
    private String horario_Trabajo;
    private BigDecimal salario;
    
	public Empleado(String puesto, String fecha_Contratacion, String horario_Trabajo, BigDecimal salario) {
		super();
		this.puesto = puesto;
		this.fecha_Contratacion = fecha_Contratacion;
		this.horario_Trabajo = horario_Trabajo;
		this.salario = salario;
	}
    
    
}