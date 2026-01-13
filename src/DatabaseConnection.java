import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/library_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres"; // vide car sudo postgres

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion réussie à PostgreSQL");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion");
            e.printStackTrace();
            return null;
        }
    }
}
