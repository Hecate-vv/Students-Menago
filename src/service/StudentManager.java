package service;
import model.Student;
import java.util.List;

public interface StudentManager {
    void addStudent(Student student); // Dodaje nowego studenta
    void removeStudent(String studentID); // Usuwa studenta po ID
    void updateStudent(Student student); // Aktualizuje dane studenta
    List<Student> displayAllStudents(); // Wyświetla wszystkich studentów
    double calculateAverageGrade(); // Oblicza średnią ocen
}
