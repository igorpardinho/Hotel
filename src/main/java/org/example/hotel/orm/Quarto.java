package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "quartos")
@ToString
@EqualsAndHashCode
public class Quarto {

    @Deprecated
    public Quarto() {

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

    @ManyToOne()
    @JoinColumn(name = "tipo_quarto_id")
    @Getter
    @Setter
    private TipoQuarto tipoQuarto;
}
