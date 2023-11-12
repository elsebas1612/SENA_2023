/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package service_helpjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service_HelpJDBC {

    public static void main(String[] args) {
        String usuario = "root";
        String password = "Colombia2020*";
        String url = "jdbc:mysql://localhost:3306/Service_Now";
        Connection conexion = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);

            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexión exitosa");

                statement = conexion.createStatement();


            } else {
                System.err.println("La conexión no se ha establecido correctamente.");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Service_HelpJDBC.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error de conexión: " + ex.getMessage());
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                    System.out.println("Conexión cerrada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Service_HelpJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


public class Service_HelpJDBC {
    public static void main(String[] args) {
        String usuarioDB = "root";
        String passwordDB = "Colombia2020*";
        String urlDB = "jdbc:mysql://localhost:3306/Service_Now";

        // Crear una instancia de DatabaseConnector
        DatabaseConnector connector = new DatabaseConnector(urlDB, usuarioDB, passwordDB);
        Connection connection = connector.getConnection();

        // Crear una instancia de UsuariosDAO
        UsuariosDAO usuariosDAO = new UsuariosDAO(connection);

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("John");
        nuevoUsuario.setApellido("Doe");
        nuevoUsuario.setDocumento("123456789");
        nuevoUsuario.setUsuario("john_doe");
        nuevoUsuario.setTelefono("555-1234");
        nuevoUsuario.setCorreo("john.doe@example.com");

        // Insertar el nuevo usuario
        boolean insercionExitosa = usuariosDAO.insertUsuario(nuevoUsuario);

        // Verificar el resultado de la inserción
        if (insercionExitosa) {
            System.out.println("Inserción exitosa");
        } else {
            System.out.println("La inserción ha fallado");
        }

        // Cerrar la conexión
        connector.closeConnection();
    }
}



