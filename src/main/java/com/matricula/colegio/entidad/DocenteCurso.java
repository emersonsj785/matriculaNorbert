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
@Table(name = "docente_curso")
public class DocenteCurso {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Docente_curso")
	private Long id_DocenteCurso;

    @ManyToOne
    @JoinColumn(name = "id_Docente")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "id_Curso")
    private Curso curso;

    // Otros campos, métodos y constructores si son necesarios
    
    public Long getId_Docente_curso() {
        return id_DocenteCurso;
    }

    public void setId_Docente_curso(Long id_Docente_curso) {
        this.id_DocenteCurso = id_Docente_curso;
    }
}
