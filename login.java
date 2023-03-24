import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.Action;

public class login extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	private JPasswordField passwordField;
	private final Action action = new SwingAction();
	public static String userid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con = null;

	public login() {
		con = JDBC.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
//		setBounds(500, 500, 500, 500);

		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setBounds(5, 5, 440, 27);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("User ID");
		lblNewLabel_1.setBounds(94, 88, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(94, 127, 61, 16);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(187, 83, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(187, 122, 130, 27);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String uid = textField.getText();
					String pwd = passwordField.getText();
					userid = uid;
					PreparedStatement pst = con
							.prepareStatement("select *from registration where user_id = ? and password = ?");
					pst.setString(1, uid);
					pst.setString(2, pwd);
					ResultSet result = pst.executeQuery();

					if (result.next() == true) {
						// navigate to the successpage
						JOptionPane.showMessageDialog(null, "You have succesfully logged in");
						ToDoList t = new ToDoList();
						t.setVisible(true);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Credentials entered are incorrect");
					}

				} catch (Exception e1) {
					System.out.println("The exception encountered is as follows :" + e1);
				}

			}
		});
		btnNewButton.setBounds(157, 205, 117, 29);
		contentPane.add(btnNewButton);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
