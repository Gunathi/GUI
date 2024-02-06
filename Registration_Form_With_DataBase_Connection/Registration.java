import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registration extends JFrame {

    private JPanel mainPane;
    private JTextField tID;
    private JTextField tname;
    private JTextField tCA;
    private JTextField tPractical;
    private JTextField tTheory;
    private JButton viewButton;
    private JButton submitButton;
    private JLabel id;
    private JLabel ca;
    private JLabel pr;
    private JLabel th;
    private JLabel stname;

    public registration(){
        setContentPane(mainPane);
        setTitle("Registration Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);
        setVisible(true);


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new viewDetails();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/marks", "root", "1234");

                    String sql = "INSERT INTO exam_marks(sid, name, ca_marks, practical_marks, theory_marks) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement st = con.prepareStatement(sql);

                    st.setString(1, tID.getText());
                    st.setString(2, tname.getText());
                    st.setInt(3, Integer.parseInt(tCA.getText()));
                    st.setInt(4, Integer.parseInt(tPractical.getText()));
                    st.setInt(5, Integer.parseInt(tTheory.getText()));

                    st.executeUpdate();
                    st.close();
                    con.close();

                    JOptionPane.showMessageDialog(mainPane, "Successful", "Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(mainPane, "JDBC not connected", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(mainPane, "DataBase not connected", "Error", JOptionPane.ERROR_MESSAGE);
                }
                tID.setText("");tname.setText("");tCA.setText("");tPractical.setText("");tTheory.setText("");
            }
        });

    }
}
