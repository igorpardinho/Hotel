package org.example.hotel.repository;

import org.example.hotel.orm.Hospede;
import org.springframework.data.repository.CrudRepository;

// Aqui extendemos os metodos CRUD da interface para ter acesso aos metodos
public interface HospedeRepository extends CrudRepository<Hospede, Long> {
}
