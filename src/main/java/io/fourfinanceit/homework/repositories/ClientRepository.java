package io.fourfinanceit.homework.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.fourfinanceit.homework.models.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	@Query("FROM Client c WHERE c.name LIKE :name and c.surname LIKE :surname")
    public Client findClientByName(@Param("name")String name, @Param("surname") String surname);
}
