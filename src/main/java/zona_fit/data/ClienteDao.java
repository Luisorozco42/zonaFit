package zona_fit.data;

import zona_fit.conexion.Conexion;
import zona_fit.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements IClienteDao{
    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";

        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar los clientes " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        boolean existe = false;
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();

            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                existe = true;
            }
        }catch (Exception e){
            System.out.println("Error al buscar cliente por id " + e.getMessage());
        }

        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }

        return existe;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "INSERT INTO cliente(nombre, apellido, membresia) " +
                "VALUES(?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al agregar un cliente " + e.getMessage());
        }

        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean moodificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con =  Conexion.getConexion();
        var sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? " +
                "WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar un cliente " + e.getMessage());
        }

        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "DELETE FROM cliente WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al eliminar cliente " + e.getMessage());
        }

        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }

        return false;
    }

    static void main() {
//        System.out.println("*** Listar Clientes ***");
        var clienteDao = new ClienteDao();

//        System.out.println("*** Buscar por Id ***");
//        var cliente1 = new Cliente(2);
//        System.out.println("Cliente antes de la busqueda: " + cliente1);
//        var encontrado = clienteDao.buscarClientePorId(cliente1);
//        if (encontrado) System.out.println("Cliente encontrado: " + cliente1);
//        else System.out.println("Cliente no encontrado: " + cliente1);

        //Agregar cliente
//        var nuevoCliente = new Cliente("Daniel", "Ortiz", 300);
//        var agregado = clienteDao.agregarCliente(nuevoCliente);
//
//        if (agregado) System.out.println("Cliente agregado: " + nuevoCliente);
//        else System.out.println("No se agrego el cliente: " + nuevoCliente);

        //modificar cliente
        var modificarCliente = new Cliente(5, "Carlos Daniel", "Ortiz", 300);
        var modificado = clienteDao.moodificarCliente(modificarCliente);
        if (modificado) System.out.println("Cliente modificado: " + modificarCliente);
        else System.out.println("No se modifico cliente: " + modificarCliente);

        System.out.println("*** Listado de Clientes ***");
        var clientes = clienteDao.listarCliente();
        clientes.forEach(System.out::println);

    }
}
