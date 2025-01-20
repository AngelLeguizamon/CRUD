public class App {
    public static void main(String[] args) {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://<TU_BASE_DE_DATOS>.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            FirebaseService service = new FirebaseService();

            // Crear usuarios
            service.createUser(new User("1", "Juan", "juan@example.com"));
            service.createUser(new User("2", "David", "david@example.com"));

            // Leer usuarios
            service.getUsers();

            // Actualizar usuario
            service.updateUser("1", "Juan Ángel", "juan.angel@example.com");

            // Eliminar usuario
            service.deleteUser("2");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
