package org.example.hotel.repository;

import org.example.hotel.orm.Hospede;
import org.springframework.data.repository.CrudRepository;

public interface HospedeRepository extends CrudRepository<Hospede, Long> {
}
