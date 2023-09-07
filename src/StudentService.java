import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
        students.add(new Student(1, "Bilbo", "Baggins", "Shire", "10"));
        students.add(new Student(2, "Samwise", "Gamgee", "Shire", "10"));
        students.add(new Student(3, "Gandalf", "the Gray", "Shire", "10"));
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
    public String[] columnsToString() {
        return new String[]{"First Name", "Last Name", "Location", "Grade"};
    }

}
