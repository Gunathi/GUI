import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class viewDetails extends JFrame{
    private JPanel marksDisplay;
    private JTable marks;
    private DefaultTableModel tableModel;

    public viewDetails() throws ClassNotFoundException, SQLException {
        setContentPane(marksDisplay);
        setMinimumSize(new Dimension(550, 474));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/marks", "root", "1234");
        String sql = "SELECT * FROM exam_marks";

        PreparedStatement st = con.prepareStatement(sql);

        ResultSet result = st.executeQuery();

        tableModel = new DefaultTableModel();
        marks.setModel(tableModel);

        tableModel.setColumnIdentifiers(new String[]{"Student ID", "Student Name", "Theory", "Practical", "CA", "Total", "Average"});

        while (result.next()){
            int theory = result.getInt("theory_marks");
            int practical = result.getInt("practical_marks");
            int ca = result.getInt("ca_marks");
            int total = theory + practical + ca;
            int average = total/3;

            Object[] row = {
                result.getString("sid"),
                result.getString("name"),
                theory,
                practical,
                ca,
                total,
                average
            };

            tableModel.addRow(row);
        }
    }
}
