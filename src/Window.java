import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class Window extends JFrame implements ActionListener {

    private final StudentService studentService = new StudentService();

    private final JButton addButton, deleteButton, updateButton, searchButton;
    private final JTextField firstNameTextField, lastNameTextField, locationTextField, gradeTextField;
    private final DefaultTableModel model;
    private final JTable table;
    private String url, user, password;

    public Window() throws HeadlessException {

        setupDataBaseSettings();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 620);
        setTitle("Student Management Window");

        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        //Text Fields

        //First Name
        JLabel firstNameLabel = new JLabel("First name: ");
        firstNameLabel.setBounds(15, 35, 150, 34);
        contentPane.add(firstNameLabel);

        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(95, 40, 200, 30);
        contentPane.add(firstNameTextField);

        //Last Name
        JLabel lastNameLabel = new JLabel("Last name: ");
        lastNameLabel.setBounds(15, 85, 150, 34);
        contentPane.add(lastNameLabel);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(95, 90, 200, 30);
        contentPane.add(lastNameTextField);

        //Location
        JLabel locationLabel = new JLabel("Location: ");
        locationLabel.setBounds(15, 135, 150, 34);
        contentPane.add(locationLabel);

        locationTextField = new JTextField();
        locationTextField.setBounds(95, 140, 200, 30);
        contentPane.add(locationTextField);

        //Grade
        JLabel gradeLabel = new JLabel("Grade: ");
        gradeLabel.setBounds(20, 185, 150, 34);
        contentPane.add(gradeLabel);

        gradeTextField = new JTextField();
        gradeTextField.setBounds(95, 190, 200, 30);
        contentPane.add(gradeTextField);


        //Buttons
        addButton = new JButton("Add");
        addButton.setBounds(400, 35, 100, 40);
        contentPane.add(addButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(400, 95, 100, 40);
        contentPane.add(searchButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(400, 175, 100, 40);
        contentPane.add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(520, 70, 100, 40);
        contentPane.add(updateButton);

        addButton.addActionListener(this);
        searchButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);


        //Table
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(studentService.columnsToString());
        table.setDefaultEditor(Objects.class, null);
        table.setRowHeight(35);
        table.setBackground(Color.white);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //add row data to table
        for (Student student :studentService.getAllStudents()) {
            model.addRow(student.getAsString());
        }

        //Setting the table to the scrollPane and into the window
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 280, 635, 290);
        contentPane.add(scrollPane);

        //Mouse Listener, adds info to text field when selecting row
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent m) {
                int numberOfRows = table.getSelectedRow();
                //getValueAt() method return object. so convert to String using toString() method
                String firstName = model.getValueAt(numberOfRows, 0).toString();
                String lastName = model.getValueAt(numberOfRows, 1).toString();
                String location = model.getValueAt(numberOfRows, 2).toString();
                String grade = model.getValueAt(numberOfRows, 3).toString();

                firstNameTextField.setText(firstName);
                lastNameTextField.setText(lastName);
                locationTextField.setText(location);
                gradeTextField.setText(grade);
            }
        });


        setVisible(true);
        setResizable(false);
    }

    private void setupDataBaseSettings() {
        try (InputStream input = new FileInputStream("resources/config.properties")) {

            Properties properties = new Properties();
            properties.load(input);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

            getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void getConnection(String url, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)){


            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                System.out.println("Student " +
                        resultSet.getString(1) + " " +
                        resultSet.getString(2) + ", " +
                        resultSet.getString(3) + " - " +
                        resultSet.getString(4));

                Student student = new Student();

                student.setFirstName(resultSet.getString(1));
                student.setLastName(resultSet.getString(2));
                student.setLocation(resultSet.getString(3));
                student.setGrade(resultSet.getString(4));

                studentService.addStudent(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public boolean addStudentToDB(String firstName, String lastName, String location, String grade) {
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE (firstname = ?, lastName = ?)");
        ){
            //TODO Redo using the arraylist from student services? Which one could be faster?
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "There's already a student with that name");
                return false;
            }

            Statement statement = connection.createStatement();

            String sql = "INSERT INTO student " +
                    "(firstName, lastName, location, grade) VALUES" +
                    " ( '" + firstName + "', " +
                    "'" + lastName + "', " +
                    "'" + location + "', " +
                    "'" + grade + "' );";

            statement.execute(sql);
            JOptionPane.showMessageDialog(null, "Insertion concluded");

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    private void updateStudentInDatabase(String name, String firstName, String lastName, String location, String grade) {
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET firstName = ?, lastName = ?, location = ?, grade = ? WHERE firstName = ?");
        ){

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, grade);
            preparedStatement.setString(5, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(addButton)) {
            if (
                    firstNameTextField.getText().isEmpty() ||
                    lastNameTextField.getText().isEmpty() ||
                    locationTextField.getText().isEmpty() ||
                    gradeTextField.getText().isEmpty()
            ) {
                JOptionPane.showMessageDialog(null, "Please fill all information.");
                return;
            }
            String[] row = new String[studentService.columnsToString().length];
            try {
                row[0] = firstNameTextField.getText();
                row[1] = lastNameTextField.getText();
                row[2] = locationTextField.getText();
                row[3] = gradeTextField.getText();

                if (addStudentToDB(row[0], row[1], row[2], row[3])) {
                    model.addRow(row);
                    Student student = new Student(row[0], row[1], row[2], row[3]);
                    studentService.addStudent(student);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Please check the information provided.");
            }
        } else if (source.equals(deleteButton)) {
            int numberOfRow = table.getSelectedRow();

            if (numberOfRow >= 0) {

                model.removeRow(numberOfRow);
                studentService.removeStudentByName(firstNameTextField.getText());
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                locationTextField.setText("");
                gradeTextField.setText("");


            } else {
                JOptionPane.showMessageDialog(null, "Select a row to delete!");
            }
        } else if (source.equals(updateButton)) {

            int numberOfRow = table.getSelectedRow();
            if (numberOfRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row");
                return;
            }
            Object str = table.getValueAt(table.getSelectedRow(), 0);
            String name = str.toString();
            try {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String location = locationTextField.getText();
                String grade = gradeTextField.getText();

                if (studentService.getStudentByFirstName(name).isPresent()) {
                    updateStudentInDatabase(name ,firstName, lastName, location, grade);
                    studentService.getStudentByFirstName(name).get().setFirstName(firstName);
                    studentService.getStudentByFirstName(name).get().setLastName(lastName);
                    studentService.getStudentByFirstName(name).get().setLocation(location);
                    studentService.getStudentByFirstName(name).get().setGrade(grade);
                }

                model.setValueAt(firstName, numberOfRow, 0);
                model.setValueAt(lastName, numberOfRow, 1);
                model.setValueAt(location, numberOfRow, 2);
                model.setValueAt(grade, numberOfRow, 3);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No row has been selected or exits.");
            }
        } else if(source.equals(searchButton)) {
            if (!firstNameTextField.getText().isEmpty()) {
                model.setRowCount(0);
                if (studentService.getStudentByFirstName(firstNameTextField.getText()).isPresent())
                    model.addRow(studentService.getStudentByFirstName(firstNameTextField.getText())
                            .get().getAsString());

            } else if (!lastNameTextField.getText().isEmpty()) {
                model.setRowCount(0);
                if (studentService.getStudentsByLastName(lastNameTextField.getText()).isPresent())
                    studentService.getStudentsByLastName(lastNameTextField.getText())
                            .get().getAsString();

            } else if (!locationTextField.getText().isEmpty()) {
                model.setRowCount(0);
                if (studentService.getStudentsByLocation(locationTextField.getText()).isPresent())
                    studentService.getStudentsByLocation(locationTextField.getText())
                            .get().getAsString();
            } else {
                model.setRowCount(0);
                studentService.getAllStudents()
                        .forEach(student ->
                                model.addRow(student.getAsString()));
                JOptionPane.showMessageDialog(null, "Search too broad, please reduce the terms.");
            }
        }
    }
}
