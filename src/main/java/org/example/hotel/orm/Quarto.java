package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "quartos")
@EqualsAndHashCode
public class Quarto {

    @Deprecated
    public Quarto() {

    }
    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;

    public Quarto(int numero,String nome,TipoQuarto quarto) {
        this.nome = nome;
        this.numero = numero;
        this.tipoQuarto = quarto;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private int numero;


    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String nome;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "tipoQuarto_id")
    private TipoQuarto tipoQuarto;


    @Override
    public String toString() {
        return "Quarto{" +
                ", id=" + id +
                ", numero=" + numero +
                ", nome='" + nome + '\'' +
                 tipoQuarto +
                '}';
    }
}
