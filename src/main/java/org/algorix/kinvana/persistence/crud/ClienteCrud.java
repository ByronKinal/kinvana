package org.algorix.kinvana.persistence.crud;

import org.algorix.kinvana.persistence.entity.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrud extends CrudRepository<ClienteEntity, Integer> {

}