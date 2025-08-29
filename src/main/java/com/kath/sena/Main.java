package com.kath.sena;

import com.kath.sena.dao.UserDAO;
import com.kath.sena.model.User;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // Insertar usuario ejemplo
        User user = new User("U0010", "Luna Fer", "Luna@example.com", "pass999", "3238547711",
                LocalDate.of(1980, 8, 17), new BigDecimal("80.5"), new BigDecimal("1.70"),
                "F", true, 1, null, null, null, null);
        if (dao.insert(user)) {
            System.out.println("Usuario insertado correctamente.");
        }

        // Listar todos
        dao.getAllUsers().forEach(u -> System.out.println(u.getUserId() + " - " + u.getName()));

        // Eliminar un usuario por ID
        boolean deleted = dao.delete("U0010"); // cambia por un ID existente
        if (deleted) {
            System.out.println("❌ Usuario eliminado.");
        } else {
            System.out.println("⚠️ Usuario no encontrado.");
        }


        //Actualizar

        // 🔄 Actualizar un usuario existente (cambio de correo)
        User updatedUser = new User("U002", "Carlos Mejía", "carlos.nuevo@example.com", "newpass123", "3112233344",
                LocalDate.of(1990, 5, 12), new BigDecimal("75.0"), new BigDecimal("1.75"),
                "M", true, 2, "Musculación", Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), null);

        boolean updated = dao.update(updatedUser);
        if (updated) {
            System.out.println("✅ Usuario actualizado correctamente.");
        } else {
            System.out.println("⚠️ Usuario no encontrado para actualizar.");
        }

    }



}
