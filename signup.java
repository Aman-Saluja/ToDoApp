import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField id;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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

	public signup() {
		con = JDBC.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Signup Form");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(4, 6, 526, 174);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblFirstName = new JLabel("Full Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setBounds(44, 100, 102, 16);
		contentPane.add(lblFirstName);

		JLabel lblNewLabel_1 = new JLabel("Email Id");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(48, 120, 80, 32);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("User ID");
		lblNewLabel_2.setBounds(61, 148, 75, 32);
		contentPane.add(lblNewLabel_2);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(61, 182, 61, 16);
		contentPane.add(lblPassword);

		name = new JTextField();
		name.setBounds(187, 95, 155, 26);
		contentPane.add(name);
		name.setColumns(10);

		email = new JTextField();
		email.setBounds(187, 123, 155, 26);
		contentPane.add(email);
		email.setColumns(10);

		id = new JTextField();
		id.setBounds(187, 151, 155, 26);
		contentPane.add(id);
		id.setColumns(10);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String fullname = name.getText();
					String userid = id.getText();
					String email_id = email.getText();
					String pwd = password.getText();

					if (fullname.equals("") || userid.equals("") || email_id.equals("") || pwd.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter all details");

					}

					else {
						PreparedStatement pst = con.prepareStatement(
								"insert into registration(full_name,user_id,email_id,password) values (?,?,?,?)");
						pst.setString(1, fullname);
						pst.setString(2, userid);
						pst.setString(3, email_id);
						pst.setString(4, pwd);

						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "User has been registered succesfully ");
						login l1 = new login();
						l1.setVisible(true);
						dispose();
					}

				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setBounds(183, 274, 117, 29);
		contentPane.add(btnNewButton);

		password = new JPasswordField();
		password.setBounds(187, 177, 153, 26);
		contentPane.add(password);
	}
}
