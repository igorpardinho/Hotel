package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @Getter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @OneToMany()
    @JoinColumn(name = "Quarto_id")
    private Set<Quarto> quartos = new HashSet<>();


    @Override
    public String toString() {
        return "TipoQuarto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }


}
