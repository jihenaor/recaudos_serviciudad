package recaudosfacade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSicesp {

    private Connection conexion;

    // Static variable reference of single_instance
    // of type Singleton
    private static ConexionSicesp single_instance = null;
 
    // Declaring a variable of type String
    public String s;
 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private ConexionSicesp() {
        crearConexion();
    }
 
    // Static method
    // Static method to create instance of Singleton class
    public static ConexionSicesp getInstance()
    {
        if (single_instance == null) {
            single_instance = new ConexionSicesp();
        }
        return single_instance;
    }


    /**
     * Método utilizado para recuperar el valor del atributo conexion
     *
     * @return conexion contiene el estado de la conexión
     *
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Método utilizado para establecer la conexión con la base de datos
     *
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
    public boolean crearConexion() {
        String ruta;

        ruta = "jdbc:firebirdsql:192.168.100.10:d:\\datosdb\\DBSICESP.FDB";
        System.out.println("Ruta:" + ruta);
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conexion = DriverManager.getConnection(ruta, "SYSDBA", "masterkey");        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     *
     * Método utilizado para realizar la instrucción SELECT
     *
     * @param sql Cadena que contiene la instrucción SQL a ejecutar
     * @return resultado regresa los registros generados por la consulta
     *
     */
    public ResultSet ejecutarSQLSelect(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return resultado;
    }
}
