package model;

public class Student {

    private String studentID;
    private String name;
    private int age;
    private double grade;

    // Konstruktor klasy Student
    public Student(String studentID, String name, int age, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Gettery i Settery
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) { // Walidacja wieku
            this.age = age;
        } else {
            throw new IllegalArgumentException("Wiek musi być liczbą dodatnią.");
        }
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) { // Walidacja oceny
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Ocena musi być w zakresie od 0,0 do 100,0.");
        }
    }

    // Metoda do wyświetlania informacji o studencie
    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}

