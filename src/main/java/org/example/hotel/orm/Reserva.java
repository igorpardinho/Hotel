package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "reservas")
@EqualsAndHashCode
public class Reserva {

    @Deprecated
    public Reserva() {

    }
    public Reserva(int numero){
        this.numero = numero;
    }

    @OneToOne
    @JoinColumn(name = "hospede_id")
    @Getter
    @Setter
    private Hospede hospede;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;



    @Getter
    @Setter
    private int numero;

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", numero=" + numero +
                '}';
    }
}
