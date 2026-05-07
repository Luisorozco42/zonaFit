package zona_fit.data;

import zona_fit.domain.Cliente;

import java.util.List;

public interface IClienteDao {
    List<Cliente> listarCliente();
    boolean buscarClientePorId(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean moodificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
