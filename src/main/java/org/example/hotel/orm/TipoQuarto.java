package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "tipos_de_quartos")
@EqualsAndHashCode
public class TipoQuarto {

    @Deprecated
    public TipoQuarto() {

    }
    public TipoQuarto(String nome) {
        this.nome = nome;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nome;

    @OneToMany
    @JoinColumn(name = "tipoQuarto_id",referencedColumnName = "id")
    private List<Quarto> quartos;


    @Override
    public String toString() {
        return "TipoQuarto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
