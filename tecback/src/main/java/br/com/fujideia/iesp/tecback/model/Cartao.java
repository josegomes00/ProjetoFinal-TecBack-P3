package br.com.fujideia.iesp.tecback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "tb_cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "O CAMPO |NUMERO| NÃO PODE ESTAR VAZIO")
    private int numero;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "O CAMPO |VALIDADE| NÃO PODE ESTAR VAZIO")
    @Future(message = "O CAMPO |VALIDADE| DEVE SER UMA DATA FUTURA")
    private LocalDate validade;

    @NotNull(message = "O CAMPO |CVV| NÃO PODE ESTAR VAZIO")
    private int cvv;
    @NotEmpty(message = "O CAMPO |NOME DO TITULAR| NÃO PODE ESTAR VAZIO")
    private String nomeTitular;
}
