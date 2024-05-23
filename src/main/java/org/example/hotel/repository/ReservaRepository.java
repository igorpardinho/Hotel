package org.example.hotel.repository;

import org.example.hotel.orm.Reserva;
import org.springframework.data.repository.CrudRepository;

// Aqui extendemos os metodos CRUD da interface para ter acesso aos metodos
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}
