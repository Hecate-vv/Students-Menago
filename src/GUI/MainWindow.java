package GUI;

import javax.swing.*;
import java.awt.*;
import model.Student;
import service.StudentManager;
import service.StudentManagerImpl;

public class MainWindow extends JFrame {

    private JTextField studentIDField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField gradeField;
    private JTextArea outputArea;
    private StudentManager manager;

    public MainWindow() {
        setTitle("System Zarządzania Studentami");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        manager = new StudentManagerImpl();

        // Główna struktura
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        // Panel danych wejściowych
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        inputPanel.add(studentIDField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        mainPanel.add(inputPanel);

        // Panel przycisków
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(e -> updateStudent());
        buttonPanel.add(updateButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(e -> displayAllStudents());
        buttonPanel.add(displayButton);

        JButton deleteButton = new JButton("Delete Selected Student");
        deleteButton.addActionListener(e -> removeStudent());
        buttonPanel.add(deleteButton);

        JButton calculateAverageButton = new JButton("Calculate Average Grade");
        calculateAverageButton.addActionListener(e -> calculateAverageGrade());
        buttonPanel.add(calculateAverageButton);

        mainPanel.add(buttonPanel);

        // Obszar wyjściowy
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane);
    }

    private void addStudent() {
        try {
            String studentID = studentIDField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            Student student = new Student(studentID, name, age, grade);
            manager.addStudent(student);
            outputArea.append("Dodano studenta: " + name + "\n");
        } catch (Exception ex) {
            outputArea.append("Błąd: " + ex.getMessage() + "\n");
        }
    }

    private void updateStudent() {
        try {
            String studentID = studentIDField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            Student student = new Student(studentID, name, age, grade);
            manager.updateStudent(student);
            outputArea.append("Zaktualizowano dane studenta o ID: " + studentID + "\n");
            displayAllStudents(); // Odśwież listę studentów
        } catch (Exception ex) {
            outputArea.append("Błąd podczas aktualizacji studenta: " + ex.getMessage() + "\n");
        }
    }

    private void displayAllStudents() {
        outputArea.setText("");
        for (Student student : manager.displayAllStudents()) {
            outputArea.append(student.getStudentID() + " | " + student.getName() + " | " + student.getAge() + " | " + student.getGrade() + "\n");
        }
    }

    private void removeStudent() {
        String studentID = studentIDField.getText();
        manager.removeStudent(studentID);
        outputArea.append("Usunięto studenta o ID: " + studentID + "\n");
        displayAllStudents();
    }

    private void calculateAverageGrade() {
        double average = manager.calculateAverageGrade();
        outputArea.append("Średnia ocen: " + average + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
