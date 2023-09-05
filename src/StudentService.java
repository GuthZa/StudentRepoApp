import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class StudentService {

    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "Bilbo Baggins"));
        }
    }

    public Stream<Student> getStudentByName(String name) {
        return students.stream().
                filter(student -> Objects.equals(student.getName(), name));
    }
}
