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

    public Quarto(int numero,String nome,TipoQuarto quarto) {
        this.nome = nome;
        this.numero = numero;
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
    private TipoQuarto tipoQuarto;




}
