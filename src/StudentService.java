import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    private void createDummyStudents() {
        students.add(new Student("Bilbo", "Baggins", "Shire", "10"));
        students.add(new Student("Samwise", "Gamgee", "Shire", "10"));
        students.add(new Student("Gandalf", "the Grey", "Shire", "10"));
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return students.stream().filter(student -> student.getFirstName().equals(firstName)).toList();
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return students.stream().filter(student -> student.getLastName().equals(lastName)).toList();

    }

    public List<Student> getStudentsByLocation(String location) {
        return students.stream().filter(student -> student.getLocation().equals(location)).toList();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudentByName(String name) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getFirstName().equals(name)) {
                students.remove(i);
                return;
            }
        }
    }

    public String[] columnsToString() {
        return new String[]{"First Name", "Last Name", "Location", "Grade"};
    }

}
