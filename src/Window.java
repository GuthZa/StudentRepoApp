import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() throws HeadlessException {
        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(10, 10, 200, 25);

        JButton button = new JButton("click");
        button.setBounds(70, 70, 100, 50);

        JLabel nameLabel = new JLabel("placeholder");
        nameLabel.setBounds(10, 150, 100, 25);

        String[][] data = {
                {"1", "Bilbo Baggins", "Shire"},
                {"2", "Samwise Gamgee", "Shire"},
                {"3", "Gandalf", "Wanderer"}};
        String[] columns = {"ID", "Name", "Location"};
        JTable table = new JTable(data, columns);

        table.setBounds(350, 10, 250, 250);
        table.setCellSelectionEnabled(false);
        table.setDefaultEditor(Object.class, null);
        table.sizeColumnsToFit(1);

        add(button);
        add(nameTextField);
        add(nameLabel);
        add(table);


        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
    }
}
