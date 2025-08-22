package org.algorix.kinvana;
import lombok.AllArgsConstructor;
import org.algorix.kinvana.dominio.service.ClienteService;
import org.algorix.kinvana.dominio.service.IClienteService;
import org.algorix.kinvana.persistence.entity.ClienteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class KinvanaApplication implements CommandLineRunner {

	@Autowired
	//Inyeccion de dependencias
	private IClienteService clienteService;

	//Crear nuestro objeto (herramienta) logger para interactuar con la consola
	private static final Logger logger = LoggerFactory.getLogger(KinvanaApplication.class);

	//Crear un objeto String para saltos de linea porque logger no maneja saltos de linea
	String sl = System.lineSeparator();//Salto de linea


	public static void main(String[] args) {
		logger.info("Aqui inicia nuestra applicacion");
		SpringApplication.run(KinvanaApplication.class, args);
		logger.info("Aqui termino nuestra aplicacion");
	}

	@Override
	public void run(String... args) throws Exception {
		KinvanaClienteApp();
	}
	private void KinvanaClienteApp(){
		logger.info("++++++APLICACION DE REGISTRO DE CLIENTES++++++");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola,opcion);
			logger.info(sl);
		}
	}

	private int mostrarMenu (Scanner consola){
		logger.info("""
             \n ++++++Aplicacion++++++
             1.Listar todos los clientes.
             2.Buscar por cliente
             3.Agregar nuevo cliente
             4.Modeficar cliente.
             5.Eliminar cliente.
             6.Salir.
             Elije una opcion: \s""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;

	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 ->{
				logger.info(sl+"++++++Listado de todos los cliente++++++"+sl);
				List<ClienteEntity> clientes = clienteService.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString()+sl));
			}
			case 2 ->{
				logger.info(sl+"*** Buscar Cliente por ID ***"+sl);
				var	codigo = Integer.parseInt(consola.nextLine());
				ClienteEntity cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null) {
					logger.info("Cliente encontrado: " + cliente + sl);
				} else {
					logger.info("Cliente NO encontrado: " + cliente + sl);
				}
			}
			case 3 ->{
				logger.info(sl+"*** Agreagar nuevo cliente ***"+sl);
				logger.info("Ingrese el nombre del cliente: ");
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido del cliente: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el telefono del cliente: ");
				var telefono = consola.nextLine();
				logger.info("Ingrese el correo del cliente: ");
				var correo = consola.nextLine();
				logger.info("Ingrese la genero del cliente: ");
				var genero = consola.nextLine();
				logger.info("Ingrese la edad del cliente: ");
				var edad = Integer.parseInt(consola.nextLine());
				var Cliente = new ClienteEntity();
				Cliente.setNombre(nombre);
				Cliente.setApellido(apellido);
				Cliente.setTelefono(telefono);
				Cliente.setCorreo(correo);
				Cliente.setGenero(genero);
				Cliente.setEdad(edad);
				clienteService.guardarCliente(Cliente);
				logger.info("Cliente agregado: "+sl+ Cliente+sl);
			}
			case 4 ->{
				logger.info(sl+"*** Modificar cliente ***"+sl);
				logger.info("Ingrese el codigo del cliente a editar");
				var codigo = Integer.parseInt(consola.nextLine());
				ClienteEntity cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null) {
					logger.info("Ingrese el nombre del cliente: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido del cliente: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el telefono del cliente: ");
					var telefono = consola.nextLine();
					logger.info("Ingrese el correo del cliente: ");
					var correo = consola.nextLine();
					logger.info("Ingrese la genero del cliente: ");
					var genero = consola.nextLine();
					logger.info("Ingrese la edad del cliente: ");
					var edad = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					cliente.setGenero(genero);
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente modificado: " + sl + cliente + sl);
				}else {
					logger.info("Cliente NO encontrado: " + sl + cliente + sl);
				}
			}
			case 5 ->{
				logger.info("ingrese el codigo del cliente a eliminar");
				var codigo = Integer.parseInt(consola.nextLine());
		        var cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null) {
					clienteService.eliminarCliente(cliente);
					logger.info("Cliente eliminado: " + sl + cliente + sl);
				} else {
					logger.info("Cliente NO encontrado: " + sl + cliente + sl);
				}
			}
			case 6 ->{
				logger.info("Eso es todo amigos");
				salir =true;
			}
			default -> logger.info("Opcion no valida");
		}
		return salir;
	}
}
