package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reservas")
@ToString
@EqualsAndHashCode
public class Reserva {

    @Deprecated
    public Reserva() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @Setter
    @Getter
    private Hospede hospede;

    @Getter
    @Setter
    private int numero;
}
