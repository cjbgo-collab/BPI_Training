package M5_Activity3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;
import java.sql.ResultSet;

public class M5_Activity3 {

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            new M5_Activity3().start();
        } catch (Exception e) {
            System.out.println("Fatal error occurred. Application terminated safely.");
        } finally {
            System.out.println("EXIT");
        }
    }

    private void start() {
        boolean running = true;

        while (running) {
            try {
                printMenu();
                MenuOption option = readOption();


switch (option) {
    case ADD_STUDENT:
        handleAddStudent();
        break;
    case ADD_COURSE:
        handleAddCourse();
        break;
    case SHOW_STUDENTS:
        handleShowStudents();
        break;
    case SHOW_COURSES:
        handleShowCourses();
        break;
    case EXIT:
        running = false;
        break;
    default:
        System.out.println("Invalid option. Please select 1, 2, 3, 4, or 0.");
}

                }
             catch (Exception ex) {
                System.out.println("An unexpected error occurred. Returning to menu.");
            }
        }
    }

    // ================= MENU =================

    private void printMenu() {
        System.out.println("================= STUDENT COURSE MANAGEMENT ==================");
        System.out.println("1 : Add Student");
        System.out.println("2 : Add Course");
        System.out.println("3 : Show Students");
        System.out.println("4 : Show Courses");
        System.out.println("0 : Exit");
        System.out.print("Choose an option : ");
    }

    private MenuOption readOption() {
        return MenuOption.from(scanner.nextLine().trim());
    }

    enum MenuOption {
        ADD_STUDENT, ADD_COURSE, SHOW_STUDENTS, SHOW_COURSES, EXIT, INVALID;


    	static MenuOption from(String input) {
    		switch (input) {
        case "1":
            return ADD_STUDENT;
        case "2":
            return ADD_COURSE;
        case "3":
            return SHOW_STUDENTS;
        case "4":
            return SHOW_COURSES;
        case "0":
            return EXIT;
        default:
            return INVALID;
    }
}

    }

    // ================= HANDLERS =================

    private void handleAddStudent() {
        try {
            String name = readText("Enter Name : ", "Name");
            int age = readNumber("Enter Age : ", "Age");
            String email = readText("Enter Email : ", "Email");

            String sql = "INSERT INTO students(name, age, email) VALUES (?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, email);
                ps.executeUpdate();

                System.out.println("Student added successfully!");
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Error: Email already exists.");
        } catch (SQLException ex) {
            System.out.println("Database error while adding student.");
        } catch (Exception ex) {
            System.out.println("Invalid input. Student not added.");
        }
    }

    private void handleAddCourse() {
        try {
            int sid = readNumber("Enter Student ID : ", "Student ID");
            String course = readText("Enter Course Name : ", "Course Name");
            int grade = readRange("Enter Grade : ", 0, 100);

            String sql = "INSERT INTO courses(student_id, course_name, grade) VALUES (?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, sid);
                ps.setString(2, course);
                ps.setInt(3, grade);
                ps.executeUpdate();

                System.out.println("Course added successfully!");
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Error: Student ID does not exist.");
        } catch (SQLException ex) {
            System.out.println("Database error while adding course.");
        } catch (Exception ex) {
            System.out.println("Invalid input. Course not added.");
        }
    }

    private void handleShowStudents() {
        try {
            String sql = "SELECT * FROM students";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                printStudentHeader();
                while (rs.next()) {
                    System.out.printf("%-3d %-25s %-5d %-1s %n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("email"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Unable to retrieve students.");
        }
    }

    private void handleShowCourses() {
        try {
            String sql = "SELECT * FROM courses";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                printCourseHeader();
                while (rs.next()) {
                    System.out.printf("%-3d %-12d %-20s %-1d %n",
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getString("course_name"),
                            rs.getInt("grade"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Unable to retrieve courses.");
        }
    }

    // ================= INPUT VALIDATION =================

    private String readText(String prompt, String field) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println(field + " cannot be empty.");
        }
    }

    private int readNumber(String prompt, String field) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println(field + " cannot be negative.");
                } else {
                    return value;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private int readRange(String prompt, int min, int max) {
        while (true) {
            int value = readNumber(prompt, "Value");
            if (value >= min && value <= max) return value;
            System.out.println("Value must be between " + min + " and " + max + ".");
        }
    }

    // ================= DISPLAY HELPERS =================

    private void printStudentHeader() {
        System.out.println("==============================================================");
        System.out.printf("%-3s %-25s %-5s %-1s %n",
                "ID", "Student Name", "Age", "Email");
        System.out.println("==============================================================");
    }

    private void printCourseHeader() {
        System.out.println("==============================================================");
        System.out.printf("%-3s %-12s %-20s %-1s %n",
                "ID", "Student ID", "Course Name", "Grade");
        System.out.println("==============================================================");
    }
}
