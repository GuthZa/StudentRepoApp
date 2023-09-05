import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() throws HeadlessException {
        String[][] data = {
                {"1", "Bilbo Baggins", "Shire"},
                {"2", "Samwise Gamgee", "Shire"},
                {"3", "Gandalf", "Wanderer"}};
        String[] column = {"ID", "NAME", "LOCATION"};

        final JTable jt=new JTable(data,column);
        jt.setCellSelectionEnabled(false);
        jt.setDefaultEditor(Object.class, null);
        JScrollPane sp=new JScrollPane(jt);

        add(sp);


        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
