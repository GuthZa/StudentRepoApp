import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

public class Window extends JFrame {

    private final String[] columns = {"ID", "First Name", " Last Name", "Department", "Grade"};

    Student one = new Student(1, "Bilbo Baggins", "Shire");

    public Window() throws HeadlessException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 970, 920);
        setTitle("Student Management Window");

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        String[][] data = {
                {"1", "Bilbo", "Baggins", "Shire", "10"},
                {"2", "Samwise", "Gamgee", "Shire", "10"},
                {"3", "Gandalf", "the Grey", "Wanderer", "20"}};
        String[] column = {"ID", "NAME", "LOCATION"};

        JButton addButton = new JButton("Add");
        addButton.setBounds(400, 120, 120, 40);
        contentPane.add(addButton);


        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setDefaultEditor(Objects.class, null);
        table.setRowHeight(35);
        table.setBackground(Color.white);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (String[] datum: data) {
            model.addRow(datum);
        }

        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 540, 925, 290);
        contentPane.add(scrollPane);


//        setSize(700, 700);
        setVisible(true);
        setResizable(false);
    }
}
