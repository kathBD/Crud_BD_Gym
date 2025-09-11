package com.kath.sena.dao;

import com.kath.sena.connection.ConnectionDB;
import com.kath.sena.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Insertar usuario
    public boolean insert(User user) {
        String sql = "INSERT INTO usuarios (usuario_id, nombre, correo, password, telefono, fecha_nacimiento, peso, estatura, sexo, esta_activo, rol_id, especialidad, horario_inicio, horario_fin) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getTelefono());
            stmt.setDate(6, Date.valueOf(user.getFechaNacimiento()));
            stmt.setBigDecimal(7, user.getPeso());
            stmt.setBigDecimal(8, user.getEstatura());
            stmt.setString(9, user.getSexo());
            stmt.setBoolean(10, user.isEstaActivo());
            stmt.setInt(11, user.getRolId());
            stmt.setString(12, user.getEspecialidad());
            stmt.setTime(13, user.getHorarioInicio());
            stmt.setTime(14, user.getHorarioFin());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error insertando usuario: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los usuarios
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectionDB.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User u = new User(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("password"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getBigDecimal("peso"),
                        rs.getBigDecimal("estatura"),
                        rs.getString("sexo"),
                        rs.getBoolean("esta_activo"),
                        rs.getInt("rol_id"),
                        rs.getString("especialidad"),
                        rs.getTime("horario_inicio"),
                        rs.getTime("horario_fin"),
                        rs.getTimestamp("fecha_registro").toLocalDateTime()
                );
                users.add(u);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error consultando usuarios: " + e.getMessage());
        }
        return users;
    }

    // Actualizar usuario
    public boolean update(User user) {
        String sql = "UPDATE usuarios SET nombre=?, correo=?, password=?, telefono=?, fecha_nacimiento=?, peso=?, estatura=?, sexo=?, esta_activo=?, rol_id=?, especialidad=?, horario_inicio=?, horario_fin=? WHERE usuario_id=?";

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getTelefono());
            stmt.setDate(5, Date.valueOf(user.getFechaNacimiento()));
            stmt.setBigDecimal(6, user.getPeso());
            stmt.setBigDecimal(7, user.getEstatura());
            stmt.setString(8, user.getSexo());
            stmt.setBoolean(9, user.isEstaActivo());
            stmt.setInt(10, user.getRolId());
            stmt.setString(11, user.getEspecialidad());
            stmt.setTime(12, user.getHorarioInicio());
            stmt.setTime(13, user.getHorarioFin());
            stmt.setInt(14, user.getUserId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error actualizando usuario: " + e.getMessage());
            return false;
        }
    }

    // Eliminar usuario
    public boolean delete(String userId) {
        String sql = "DELETE FROM usuarios WHERE usuario_id=?";

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error eliminando usuario: " + e.getMessage());
            return false;
        }
    }
}
