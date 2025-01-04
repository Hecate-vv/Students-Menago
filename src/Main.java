import java.util.List;
import model.Student;
import service.StudentManagerImpl;
import service.StudentManager;
import database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        // Tworzenie tabeli, jeśli nie istnieje
        DatabaseConnection.createTableIfNotExists();

        // Tworzymy instancję implementacji StudentManager
        StudentManager manager = new StudentManagerImpl();

        // Dodawanie studentów
        System.out.println("Dodawanie studentów:");
        manager.addStudent(new Student("S001", "Anna Nowak", 20, 85.5));
        manager.addStudent(new Student("S002", "Jan Kowalski", 22, 90.0));
        manager.addStudent(new Student("S003", "Maria Wiśniewska", 19, 78.0));

        // Wyświetlanie wszystkich studentów
        System.out.println("\nLista studentów:");
        List<Student> students = manager.displayAllStudents();
        for (Student student : students) {
            student.displayInfo();
            System.out.println();
        }

        // Aktualizacja studenta
        System.out.println("Aktualizacja studenta S001:");
        manager.updateStudent(new Student("S001", "Anna Nowak", 21, 88.0));

        // Wyświetlanie zaktualizowanej listy studentów
        System.out.println("\nLista studentów po aktualizacji:");
        students = manager.displayAllStudents();
        for (Student student : students) {
            student.displayInfo();
            System.out.println();
        }

        // Usuwanie studenta
        System.out.println("Usuwanie studenta S002:");
        manager.removeStudent("S002");

        // Wyświetlanie listy studentów po usunięciu
        System.out.println("\nLista studentów po usunięciu:");
        students = manager.displayAllStudents();
        for (Student student : students) {
            student.displayInfo();
            System.out.println();
        }

        // Obliczanie średniej ocen
        System.out.println("Średnia ocen:");
        double averageGrade = manager.calculateAverageGrade();
        System.out.println("Średnia ocen wszystkich studentów: " + averageGrade);
    }
}
