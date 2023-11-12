/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package service_helpjdbc;

import java.sql.Connection;
import java.util.List;

public class Service_HelpJDBC {
    public static void main(String[] args) {
        String usuarioDB = "root";
        String passwordDB = "Colombia2020*";
        String urlDB = "jdbc:mysql://localhost:3306/service_now";

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

        // Consultar usuarios
        List<Usuario> usuarios = usuariosDAO.consultarUsuarios();
        System.out.println("Usuarios en la base de datos:");
        for (Usuario u : usuarios) {
            System.out.println(u.getUserID() + ": " + u.getNombre() + " " + u.getApellido() + " - " + u.getCorreo());
        }

        // Actualizar un usuario (supongamos que el primer usuario en la lista será actualizado)
        if (!usuarios.isEmpty()) {
            Usuario usuarioAActualizar = usuarios.get(0);
            usuarioAActualizar.setNombre("NuevoNombre");
            boolean actualizacionExitosa = usuariosDAO.actualizarUsuario(usuarioAActualizar);
            if (actualizacionExitosa) {
                System.out.println("Actualización exitosa");
            } else {
                System.out.println("La actualización ha fallado");
            }
        }

        // Supongamos que quieres eliminar el usuario con ID 1 (suponiendo que existe)
        int usuarioIDAEliminar = 1;
        boolean eliminacionExitosa = usuariosDAO.eliminarUsuario(usuarioIDAEliminar);

        // Verificar el resultado de la eliminación
        if (eliminacionExitosa) {
            System.out.println("Eliminación exitosa");
        } else {
            System.out.println("La eliminación ha fallado");
        }

        // Cerrar la conexión
        connector.closeConnection();
    }
}



