package org.example.hotel.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Criando a tabela no banco de dados
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

    // Relação 1 pra 1 das tabelas (1 Reserva para 1 Hospede)
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
