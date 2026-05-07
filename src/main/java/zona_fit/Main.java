package zona_fit;

import zona_fit.conexion.Conexion;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        var conexion = Conexion.getConexion();
        if (conexion != null)System.out.println("Conexion exitosa: " + conexion);
        else System.out.println("Error al conectarse");
    }
}
