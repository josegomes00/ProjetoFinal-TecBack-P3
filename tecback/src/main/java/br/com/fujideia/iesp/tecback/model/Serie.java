package br.com.fujideia.iesp.tecback.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "tb_serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty (message = "O CAMPO |TÍTULO| NÃO PODE ESTAR VAZIO")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    private LocalDate anoEstreia;
    private String tempoDuracao;
    private String relevancia;

    @Column(name = "ds_sinopse", length = 500)
    private String sinopse;

    private String trailer;

}
