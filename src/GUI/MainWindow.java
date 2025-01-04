package GUI;

import model.Student;
import service.StudentManager;
import service.StudentManagerImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JTextField studentIDField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField gradeField;
    private JTextArea outputArea;

    private StudentManager manager;

    public MainWindow() {
        setTitle("System Zarządzania Studentami");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        manager = new StudentManagerImpl();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

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

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        inputPanel.add(addButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(e -> displayAllStudents());
        inputPanel.add(displayButton);

        JButton deleteButton = new JButton("Delete Selected Student");
        deleteButton.addActionListener(e -> removeStudent());
        inputPanel.add(deleteButton);

        JButton calculateAverageButton = new JButton("Calculate Average Grade");
        calculateAverageButton.addActionListener(e -> calculateAverageGrade());
        inputPanel.add(calculateAverageButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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