import java.util.ArrayList;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String location;
    private String grade;

    public Student(int id, String firstName, String lastName, String location, String grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGrade() {
        return grade;
    }

    public String[] getString() {
        return new String[] {firstName, lastName, location, grade};
    }
}
