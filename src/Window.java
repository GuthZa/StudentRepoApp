import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Window extends JFrame implements ActionListener {

    private StudentService studentService;
    private ArrayList<Student> students;

    public Window() throws HeadlessException {
        studentService = new StudentService();
        students = studentService.getAllStudents();

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

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setBounds(95, 40, 200, 30);
        contentPane.add(firstNameTextField);

        //Last Name
        JLabel lastNameLabel = new JLabel("Last name: ");
        lastNameLabel.setBounds(15, 85, 150, 34);
        contentPane.add(lastNameLabel);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setBounds(95, 90, 200, 30);
        contentPane.add(lastNameTextField);

        //Location
        JLabel locationLabel = new JLabel("Location: ");
        locationLabel.setBounds(15, 135, 150, 34);
        contentPane.add(locationLabel);

        JTextField locationTextField = new JTextField();
        locationTextField.setBounds(95, 140, 200, 30);
        contentPane.add(locationTextField);

        //Grade
        JLabel gradeLabel = new JLabel("Grade: ");
        gradeLabel.setBounds(20, 185, 150, 34);
        contentPane.add(gradeLabel);

        JTextField gradeTextField = new JTextField();
        gradeTextField.setBounds(95, 190, 200, 30);
        contentPane.add(gradeTextField);


        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(400, 35, 100, 40);
        contentPane.add(addButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(400, 95, 100, 40);
        contentPane.add(searchButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(400, 175, 100, 40);
        contentPane.add(deleteButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(520, 70, 100, 40);
        contentPane.add(updateButton);

        addButton.addActionListener(this);
        searchButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);


        //Table
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
