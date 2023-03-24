import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
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
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome to your TO DO LIST Application");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setBounds(35, 16, 380, 48);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Are you an existing user ?");
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setBounds(67, 104, 170, 76);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login l1 = new login();
					l1.setVisible(true);
					dispose();

				} catch (Exception e1) {
					System.out.println("Error encountered while opening opening Login Page" + e1);
				}
			}
		});
		btnNewButton.setBounds(249, 104, 117, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("No");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					signup s = new signup();
					s.setVisible(true);
					dispose();
				} catch (Exception e1) {
					System.out.println("Error in opening registeration window");
				}
			}
		});
		btnNewButton_1.setBounds(249, 151, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}
