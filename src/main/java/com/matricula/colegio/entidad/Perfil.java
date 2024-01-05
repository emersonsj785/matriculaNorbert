package com.matricula.colegio.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Perfil;
    
    @ManyToOne
    @JoinColumn(name = "id_Rol")
    private Rol rol;

    @NotBlank(message = "El nombre de Perfil no puede ser vacio.")
    private String nombre_Perfil;
    
    @NotBlank(message = "La descripción de Perfil no puede ser vacio.")
    private String descripcion;

    public Perfil(String nombre_Perfil, String descripcion)
    {
        this.nombre_Perfil = nombre_Perfil;
        this.descripcion = descripcion;
    }
}