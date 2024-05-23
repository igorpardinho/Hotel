package org.example.hotel.repository;

import org.example.hotel.orm.Quarto;
import org.springframework.data.repository.CrudRepository;

// Aqui extendemos os metodos CRUD da interface para ter acesso aos metodos
public interface QuartoRepository extends CrudRepository<Quarto, Long> {
}
