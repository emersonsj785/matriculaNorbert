package com.matricula.colegio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docentecurso_seccion")
public class DocenteCursoSeccion
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Doce_curso_seccion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_Docente_curso")
    private DocenteCurso docenteCurso;

    @ManyToOne
    @JoinColumn(name = "id_Seccion")
    private Seccion seccion;
}
