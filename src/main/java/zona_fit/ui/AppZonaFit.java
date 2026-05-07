package zona_fit.ui;

import zona_fit.data.ClienteDao;
import zona_fit.data.IClienteDao;
import zona_fit.domain.Cliente;

import java.util.Scanner;

public class AppZonaFit {

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        IClienteDao clienteDao = new ClienteDao();
        int opcion;

        do {
            System.out.println("*** Bienvenido a la aplicacion de Zona Fit ***");
            System.out.println("""
                    \tMenu
                    \t1. Listar los clientes
                    \t2. Agregar un cliente
                    \t3. Modificar un cliente
                    \t4. Eliminar un cliente
                    \t5. Salir del sistema
                    """);
            System.out.print("Ingrese a continuacion que desea realizar: ");
            opcion = Integer.parseInt(scanner.nextLine());
            ejecutarOpcion(scanner, clienteDao, opcion);

        } while (opcion != 5);
    }

    private void ejecutarOpcion(Scanner scanner, IClienteDao clienteDao, int opcion){
        switch (opcion) {
            case 1 -> System.out.println(clienteDao.listarCliente());
            case 2 -> prepararAgregarCliente(scanner, clienteDao);
            case 3 -> prepararModificarCliente(scanner, clienteDao);
            case 4 -> prepararEliminarCliente(scanner, clienteDao);
            case 5 -> System.out.println("Cerrando el sistema...\nTenga un buen dia");
            default -> System.out.println("Por favor elija una de las opciones anteriores");
        }
    }

    private void prepararAgregarCliente(Scanner scanner, IClienteDao clienteDao) {
        System.out.println("\nIngrese los datos del cliente por favor");
        Cliente cliente = new Cliente();
        try {
            System.out.print("Nombre: " );
            cliente.setNombre(scanner.nextLine());
            System.out.print("Apellido: ");
            cliente.setApellido(scanner.nextLine());
            System.out.print("Membresia: ");
            cliente.setMembresia(Integer.parseInt(scanner.nextLine()));

            clienteDao.agregarCliente(cliente);
            System.out.println("Cliente agregado correctamente");
        }catch (Exception e){
            System.out.println("Error al introducir los datos " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void prepararModificarCliente(Scanner scanner, IClienteDao clienteDao) {
        Cliente cliente = new Cliente();
        boolean existeCliente;
        try{
            System.out.print("\nIngrese el id del cliente a modificar: ");
            cliente.setId(Integer.parseInt(scanner.nextLine()));
            existeCliente = clienteDao.buscarClientePorId(cliente);
            if (existeCliente){
                System.out.print("Ingrese el nombre del cliente: ");
                cliente.setNombre(scanner.nextLine());
                System.out.print("Ingrese el apellido del cliente: ");
                cliente.setApellido(scanner.nextLine());
                System.out.print("Ingrese la membresia del cliente: ");
                cliente.setMembresia(Integer.parseInt(scanner.nextLine()));

                clienteDao.moodificarCliente(cliente);
                System.out.println("Cliente moodificado correctamente");
            }else System.out.println("El cliente no existe por favor de ingresar un cliente existente");

        }catch (Exception e){
            System.out.println("Error al preparar los datos: " + e.getMessage());
        }
    }

    private void prepararEliminarCliente(Scanner scanner, IClienteDao clienteDao){
        Cliente cliente = new Cliente();
        System.out.print("Ingrese el id del cliente a eliminar: ");

        try {
            cliente.setId(Integer.parseInt(scanner.nextLine()));
            var existeCliente = clienteDao.eliminarCliente(cliente);

            if (existeCliente) System.out.println("Cliente elminado apropiadamente");
            else System.out.println("El cliente no existe, por lo tanto no se elimino nada");

        }catch (Exception e){
            System.out.println("Error al preparar los datos" + e.getMessage());
        }

    }
}
