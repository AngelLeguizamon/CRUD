import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseService {
    private final DatabaseReference database;

    public FirebaseService() {
        this.database = FirebaseDatabase.getInstance().getReference("users");
    }

    // Crear un usuario
    public void createUser(User user) {
        database.child(user.getId()).setValueAsync(user);
        System.out.println("Usuario creado: " + user.getName());
    }

    // Leer usuarios
    public void getUsers() {
        database.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                System.out.println("Usuarios: " + task.getResult().getValue());
            } else {
                System.err.println("Error al leer usuarios: " + task.getException());
            }
        });
    }

    // Actualizar un usuario
    public void updateUser(String userId, String name, String email) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", name);
        updates.put("email", email);

        database.child(userId).updateChildrenAsync(updates);
        System.out.println("Usuario actualizado: " + userId);
    }

    // Eliminar un usuario
    public void deleteUser(String userId) {
        database.child(userId).removeValueAsync();
        System.out.println("Usuario eliminado: " + userId);
    }
}
