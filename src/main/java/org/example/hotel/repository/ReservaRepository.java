package org.example.hotel.repository;

import org.example.hotel.orm.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}
