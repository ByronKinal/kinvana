package org.algorix.kinvana.dominio.service;

import org.algorix.kinvana.persistence.entity.ClienteEntity;

import java.util.List;

public interface IClienteService {
    //firma de metodos
    public List<ClienteEntity> listarClientes();
    public ClienteEntity buscarClientePorId(Integer codigo);
    public void guardarCliente(ClienteEntity cliente);
    public void eliminarCliente(ClienteEntity cliente);

}
