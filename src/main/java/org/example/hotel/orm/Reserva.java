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
    public Reserva(int numero){
        this.numero = numero;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Getter
    @Setter
    private int numero;
}
