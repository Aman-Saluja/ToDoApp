import java.awt.BorderLayout;
import java.sql.Connection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToDoList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	public int rowid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoList frame = new ToDoList();
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

	public ToDoList() {
		con = JDBC.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("MY TASK LIST");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(141, 6, 146, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Urgent Tasks");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(17, 83, 83, 16);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(112, 77, 130, 76);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Other Tasks");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(17, 176, 83, 16);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(112, 176, 130, 90);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String imp_tasks = textField.getText();
					String other_tasks = textField_1.getText();
					String id = login.userid;
					PreparedStatement pst = con
							.prepareStatement("insert into Tasks (important,other,user_id) values (?,?,?)");
					pst.setString(1, imp_tasks);
					pst.setString(2, other_tasks);
					pst.setString(3, id);

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Tasks added successfully");
					textField.setText("");
					textField_1.setText("");
					int a;
					PreparedStatement pst1 = con.prepareStatement("select * from Tasks where user_id = ?");
					pst1.setString(1, id);
					ResultSet set = pst1.executeQuery();
					ResultSetMetaData rd = (ResultSetMetaData) set.getMetaData();
					a = rd.getColumnCount();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);

					while (set.next() == true) {
						Vector v2 = new Vector();
						for (int i = 1; i <= a; i++) {
//							v2.add(set.getString("id"));
							v2.add(set.getString("important"));
							v2.add(set.getString("other"));

						}
						model.addRow(v2);

					}
				} catch (Exception e1) {
					System.out.println("The Folloowing exception has occured in the program" + e1);
				}
			}
		});
		btnNewButton.setBounds(124, 343, 96, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Edit/Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					String imp = textField.getText();
					String other = textField_1.getText();
					String uid = login.userid;

					PreparedStatement pst = con.prepareStatement("update Tasks set important=?,other=? where id=?");
					pst.setString(1, imp);
					pst.setString(2, other);
					pst.setInt(3, rowid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Your To Do List has been updated");
					textField_1.setText("");
					int a;
					PreparedStatement pst1 = con.prepareStatement("select * from Tasks where user_id = ?");
					pst1.setString(1, uid);
					ResultSet set = pst1.executeQuery();
					ResultSetMetaData rd = (ResultSetMetaData) set.getMetaData();
					a = rd.getColumnCount();
					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
					model1.setRowCount(0);

					while (set.next() == true) {
						Vector v2 = new Vector();
						for (int i = 1; i <= a; i++) {
//							v2.add(set.getString("id"));
							v2.add(set.getString("important"));
							v2.add(set.getString("other"));

						}
						model1.addRow(v2);

					}
				} catch (Exception e1) {
					System.out.println("The following exception occured in Add/edit/update" + e1);
				}
			}
		});
		btnNewButton_1.setBounds(251, 343, 101, 29);
		contentPane.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 47, 251, 244);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
//				int selected_id = Integer.parseInt(model.getValueAt(row, 0).toString());
				textField.setText(model.getValueAt(row, 0).toString());
				textField_1.setText(model.getValueAt(row, 1).toString());
				String imp_tasks = textField.getText();
				String other_tasks = textField_1.getText();
				PreparedStatement pst;
				try {
					pst = con.prepareStatement("select id from Tasks where important=? and other=?");
					pst.setString(1, imp_tasks);
					pst.setString(2, other_tasks);

					ResultSet set = pst.executeQuery();
					while (set.next() == true) {
						rowid = set.getInt(1);

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error encountered in mouse click " + e1);
				}

			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { /** "ID", **/
				"IMP Tasks", "Other Tasks" }));

		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close
				dispose();
			}
		});
		btnNewButton_2.setBounds(389, 343, 96, 29);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("View Tasks");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String uid = login.userid;
					int a;
					PreparedStatement pst1 = con.prepareStatement("select * from Tasks where user_id = ?");
					pst1.setString(1, uid);
					ResultSet set = pst1.executeQuery();
					ResultSetMetaData rd = (ResultSetMetaData) set.getMetaData();
					a = rd.getColumnCount();
					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
					model1.setRowCount(0);

					while (set.next() == true) {
						Vector v2 = new Vector();
						for (int i = 1; i <= a; i++) {
							v2.add(set.getString("important"));
							v2.add(set.getString("other"));

						}
						model1.addRow(v2);

					}

				} catch (Exception e1) {
					System.out.println("Error in displaying Tasks" + e1);
				}
			}
		});
		btnNewButton_3.setBounds(6, 343, 96, 29);
		contentPane.add(btnNewButton_3);
	}
}
