package org.example.hotel.repository;

import org.example.hotel.orm.TipoQuarto;
import org.springframework.data.repository.CrudRepository;

// Aqui extendemos os metodos CRUD da interface para ter acesso aos metodos
public interface TipoQuartoRepository extends CrudRepository<TipoQuarto, Long> {
}
