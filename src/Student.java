public class Student {
    private String firstName;
    private String lastName;
    private String location;
    private String grade;

    public Student() {}

    public Student(String firstName, String lastName, String location, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.grade = grade;
        System.out.println("Student created" + this);
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

    public String[] getAsString() {
        return new String[] {firstName, lastName, location, grade};
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
