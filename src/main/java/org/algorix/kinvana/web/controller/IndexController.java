package org.algorix.kinvana.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.algorix.kinvana.KinvanaApplicationWeb;
import org.algorix.kinvana.dominio.service.IClienteService;
import org.algorix.kinvana.persistence.crud.ClienteCrud;
import org.algorix.kinvana.persistence.entity.ClienteEntity;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {
    @Autowired
    IClienteService clienteService;
    private List<ClienteEntity> clientes;
    private ClienteEntity clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(KinvanaApplicationWeb.class);
    Logger looger = LoggerFactory.getLogger(IndexController.class);


    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));

    }


    public void agregarCliente() {
        this.clienteSeleccionado = new ClienteEntity();
    }

    public void guardarCliente() {
        looger.info("Cliente a guardar: " + this.clienteSeleccionado);
        if (this.clienteSeleccionado.getCodigoCliente() == null) {
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Agregado"));
        }
        else {
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente Modificado"));
        }
        //ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente","formulario-cliente:tabla-clientes");
        this.clienteSeleccionado = null;
    }
    public void eliminarCliente() {
        //mostrar en consola
        looger.info("Cliente a eliminar: " + this.clienteSeleccionado);
        //llamar a nuesto servicio de eliminarcion de cliente
        this.clienteService.eliminarCliente(clienteSeleccionado);
        //eliminarlo de la lista clientes
        this.clientes.remove(this.clienteSeleccionado);
        //limpiar nuestro cliente seleccionado
        this.clienteSeleccionado = null;
        //enviar un mensaje emergente de confirmacion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado"));
        //actualizar la tabla con ajax
        PrimeFaces.current().ajax().update(
                "formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes"
        );
    }
}
