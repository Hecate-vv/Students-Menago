package service; // Jeśli klasa jest w pakiecie 'service'
import model.Student;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManagerImpl implements StudentManager {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDouble(4, student.getGrade());

            pstmt.executeUpdate();
            System.out.println("Dodano studenta: " + student.getName());
        } catch (SQLException e) {
            System.err.println("Błąd podczas dodawania studenta: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usunięto studenta o ID: " + studentID);
            } else {
                System.out.println("Nie znaleziono studenta o ID: " + studentID);
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas usuwania studenta: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setString(4, student.getStudentID());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Zaktualizowano dane studenta o ID: " + student.getStudentID());
            } else {
                System.out.println("Nie znaleziono studenta o ID: " + student.getStudentID());
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas aktualizacji danych studenta: " + e.getMessage());
        }
    }

    @Override
    public List<Student> displayAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("studentID"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania listy studentów: " + e.getMessage());
        }
        return students;
    }

    @Override
    public double calculateAverageGrade() {
        String sql = "SELECT AVG(grade) AS average FROM students";
        double average = 0;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                average = rs.getDouble("average");
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas obliczania średniej ocen: " + e.getMessage());
        }
        return average;
    }
}