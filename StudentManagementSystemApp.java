package studentmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date; 

class Student {
    private String name;
    private int rollNumber;
    private Date dateOfBirth; // Adding date of birth
    private String grade;
    private String address;
    private String phoneNumber;

    public Student(String name, int rollNumber, Date dateOfBirth, String grade, String address, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.dateOfBirth = dateOfBirth;
        this.grade = grade;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

 public Student(String name2, int rollNumber2, String grade2, String dateOfBirth2, String address2,
			String phoneNumber2) {
		// TODO Auto-generated constructor stub
	}

	// Add getters and setters for the new attributes
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Date of Birth: " + dateOfBirth +
               ", Grade: " + grade + ", Address: " + address + ", Phone Number: " + phoneNumber;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class StudentManagementSystemApp {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
            case 1:
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                System.out.print("Enter roll number: ");
                int rollNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter grade: ");
                String grade = scanner.nextLine();
                System.out.print("Enter date of birth (yyyy-MM-dd): ");
                String dateOfBirthStr = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();

                if (name.isEmpty() || grade.isEmpty() || dateOfBirthStr.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
                    System.out.println("All fields must be filled in.");
                } else {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
                        Student newStudent = new Student(name, rollNumber, dateOfBirth, grade, address, phoneNumber);
                        system.addStudent(newStudent);
                        System.out.println("Student added successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
                break;


                case 2:
                    System.out.print("Enter the roll number of the student to remove: ");
                    int rollNumberToRemove = scanner.nextInt();
                    if (system.searchStudent(rollNumberToRemove) != null) {
                        system.removeStudent(rollNumberToRemove);
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the roll number of the student to search: ");
                    int rollNumberToSearch = scanner.nextInt();
                    Student foundStudent = system.searchStudent(rollNumberToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    system.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
