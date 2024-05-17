package org.example.hotel.orm;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "hospedes")
@EqualsAndHashCode
public class Hospede {

    @Deprecated
    public Hospede() {

    }

    public Hospede(String nome, String cpf, String telefone) {
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome = nome;

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
                '}';
    }
}
