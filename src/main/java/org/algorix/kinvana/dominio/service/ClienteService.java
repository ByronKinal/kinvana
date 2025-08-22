package org.algorix.kinvana.dominio.service;

import org.algorix.kinvana.persistence.crud.ClienteCrud;
import org.algorix.kinvana.persistence.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    //Inyeccion de depencia de mi Repositorio (ClienteCrud) [CLienteRepository;]
    @Autowired
    private ClienteCrud crud;


    @Override
    public  List<ClienteEntity> listarClientes() {
        List<ClienteEntity> Clientes = crud.findAll();
        return Clientes;
    }

    @Override
    public ClienteEntity buscarClientePorId(Integer codigo) {
        ClienteEntity cliente = crud.findById(codigo).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(ClienteEntity cliente) {
        crud.save(cliente);//agregar nuevo y editar

    }

    @Override
    public void eliminarCliente(ClienteEntity cliente) {
        crud.delete(cliente);
    }
}
