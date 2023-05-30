package br.com.fujideia.iesp.tecback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_novoUser")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "O CAMPO |NOME| NÃO PODE ESTAR VAZIO")
    private String nomeCompleto;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Email(message = "O |E-MAIL| DEVE SER VÁLIDO")
    private String email;

    @NotEmpty(message = "O CAMPO |SENHA| NÃO PODE ESTAR VAZIO")
    private String senha;
    @NotEmpty(message = "O CAMPO |CONFIRMAR SENHA| NÃO PODE ESTAR VAZIO")
    private String confirmarSenha;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @NotNull(message = "O OBJETO |CARTÃO| NÃO PODE ESTAR VAZIO")
    private Cartao cartao;

    @CPF(message = "O CAMPO |CPF/CNJP| NÃO PODE ESTAR VAZIO")
    private String cpfOuCnpj;


    @AssertTrue(message = "A SENHA E A CONFIRMAÇÃ DEVEM SER IGUAIS")
    private boolean isSenhaConfirmada() {
        return senha != null && senha.equals(confirmarSenha);
    }

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Favoritos> favoritos = new ArrayList<>();

}
