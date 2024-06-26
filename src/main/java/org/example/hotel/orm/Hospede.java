package org.example.hotel.orm;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


//Criando a tabela no banco de dados
@Entity
@Table(name = "hospedes")
@EqualsAndHashCode
public class Hospede {

    @Deprecated
    public Hospede() {

    }

    // Relação 1 para muitos entre as tabelas (1 Hospede para muitos quartos)
    @OneToMany()
    @Getter
    @Setter
    private Set<Quarto> quartos = new HashSet<>();

    // Relação 1 para 1 entre as tabelas( 1 hospede para 1 reserva)
    @OneToOne
    @JoinColumn(name = "reserva_id")
    @Getter
    @Setter
    private Reserva reserva;

    public Hospede(String nome, String cpf, String telefone, Set quartos, Reserva reserva) {
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome = nome;
        this.quartos = quartos;
        this.reserva = reserva;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String cpf;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String telefone;

    @Override
    public String toString() {
        return "Hospede{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", quartos=" + quartos +
                 reserva +
                '}';
    }
}
