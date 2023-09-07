import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class StudentService {

    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
        students.add(new Student(1, "Bilbo", "Baggins", "Shire", "10"));
        students.add(new Student(2, "Samwise", "Gamgee", "Shire", "10"));
        students.add(new Student(3, "Gandalf", "the Gray", "Shire", "10"));
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    public String[] columnsToString() {
        return new String[]{"ID", "First Name", "Last Name", "Location", "Grade"};
    }

}
