package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tipos_de_quartos")
@ToString
@EqualsAndHashCode
public class TipoQuarto {

    @Deprecated
    public TipoQuarto() {

    }

    public TipoQuarto(Quarto quarto){
        this.quarto = quarto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nome;

    @OneToOne
    private Quarto quarto;

}
