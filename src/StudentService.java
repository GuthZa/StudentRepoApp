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

    public void addStudent(String firstName, String lastname, String location, String grade) {
        int id = students.get(students.size() - 1).getId() + 1; //creates an id that is 1 larger than last students id
        students.add(new Student(id, firstName, lastname, location, grade));
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
