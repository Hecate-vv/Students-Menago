package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL do połączenia z bazą SQLite (plik "students.db" w katalogu projektu)
    private static final String DATABASE_URL = "jdbc:sqlite:students.db";

    /**
     * Metoda do nawiązania połączenia z bazą danych.
     *
     * @return Obiekt Connection do bazy danych.
     * @throws SQLException Jeśli wystąpi problem z połączeniem.
     */
    public static Connection connect() throws SQLException {
        Connection connection = null;
        try {
            // Nawiązanie połączenia
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Połączenie z bazą danych zostało nawiązane.");
        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
            throw e;
        }
        return connection;
    }

    /**
     * Metoda do utworzenia tabeli students, jeśli nie istnieje.
     */
    public static void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "studentID TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "grade REAL NOT NULL" +
                ");";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Tabela students została utworzona (jeśli nie istniała).\n");
        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabeli students: " + e.getMessage());
        }
    }

    /**
     * Metoda testowa do sprawdzenia połączenia.
     */
    public static void main(String[] args) {
        try (Connection connection = connect()) {
            if (connection != null) {
                System.out.println("Testowe połączenie zakończone sukcesem.");
            }
        } catch (SQLException e) {
            System.err.println("Nie udało się nawiązać testowego połączenia.");
        }
    }
}
