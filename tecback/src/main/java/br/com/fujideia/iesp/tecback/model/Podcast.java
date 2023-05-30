package br.com.fujideia.iesp.tecback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_podcast")
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O CAMPO |TITULO| NÃO PODE ESTAR VAZIO")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    private LocalDate dataPublicacao;
    private String duracao;
    private String autor;

    @Column(name = "ds_descricao", length = 500)
    private String descricao;


}
