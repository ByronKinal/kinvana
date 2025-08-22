package org.algorix.kinvana.persistence.crud;

import org.algorix.kinvana.persistence.entity.ClienteEntity;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteCrud extends JpaRepository<ClienteEntity, Integer> {
    //Puede sustituir al DAO
    //Esta Interfaz tiene todos los metodos genericos del CRUD

}