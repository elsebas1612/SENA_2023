/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service_helpjdbc;

/**
 *
 * @author mendozacruz.20
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    private Connection connection;

    public UsuariosDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (Nombre, Apellido, Documento, Usuario, Telefono, Correo) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getApellido());
                statement.setString(3, usuario.getDocumento());
                statement.setString(4, usuario.getUsuario());
                statement.setString(5, usuario.getTelefono());
                statement.setString(6, usuario.getCorreo());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> consultarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuarios";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setUserID(resultSet.getInt("UserID"));
                        usuario.setNombre(resultSet.getString("Nombre"));
                        usuario.setApellido(resultSet.getString("Apellido"));
                        usuario.setDocumento(resultSet.getString("Documento"));
                        usuario.setUsuario(resultSet.getString("Usuario"));
                        usuario.setTelefono(resultSet.getString("Telefono"));
                        usuario.setCorreo(resultSet.getString("Correo"));
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        try {
            String sql = "UPDATE usuarios SET Nombre=?, Apellido=?, Documento=?, Usuario=?, Telefono=?, Correo=? WHERE UserID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getApellido());
                statement.setString(3, usuario.getDocumento());
                statement.setString(4, usuario.getUsuario());
                statement.setString(5, usuario.getTelefono());
                statement.setString(6, usuario.getCorreo());
                statement.setInt(7, usuario.getUserID());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int userId) {
        try {
            String sql = "DELETE FROM usuarios WHERE UserID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
            return false;
        }
    }
}
