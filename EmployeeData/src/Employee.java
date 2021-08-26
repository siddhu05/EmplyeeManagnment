import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class Employee {

	private JFrame frame;
	private JTextField jtxtEmpID;
	private JTable table;
	private JTextField jtxtEmpName;
	private JTextField jtxtEmpAge;
	
	Connection conn =null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public void updateTable()
	{
		conn = EmployeeData.ConnectionDB();
		
		if(conn!=null)
		{
			String sql ="Select EmpID,EmpName,EmpAge";
		
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			Object[] columnDats = new Object[8];
			
			while(rs.next()) {
				columnDats[0]=rs.getString ("EmpID");
				columnDats[1]=rs.getString ("EmpName");
				columnDats[2]=rs.getString ("EmpAge");
				
				model.addRow(columnDats);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
		conn = EmployeeData.ConnectionDB();
		Object col[] = {"EmpID","EmpName","EmpAge"};
		model.setColumnIdentifiers(col);
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		jtxtEmpID = new JTextField();
		jtxtEmpID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtEmpID.setBounds(321, 102, 117, 37);
		frame.getContentPane().add(jtxtEmpID);
		jtxtEmpID.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String sql="INSERT EMPLOYEE (EmpID,EmpName,EmpAge)VALUES(?,?,?)";
			
			try{
				pst = conn.prepareStatement(sql);
				pst.setString(1,jtxtEmpID.getText());
				pst.setString(2,jtxtEmpName.getText());
				pst.setString(3,jtxtEmpAge.getText());
				pst.execute();
				pst.close();
			}
			catch(Exception ev)
			{
				JOptionPane.showMessageDialog(null,"System Updated");
			}
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			model.addRow(new Object[]
			{
				jtxtEmpID.getText(),
				jtxtEmpName.getText(),
				jtxtEmpAge.getText(),
			});
		}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(13, 359, 127, 21);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(651, 47, 478, 305);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EmpID", "EmpName", "EmpAge"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("EmpID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(44, 108, 96, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEmpname = new JLabel("EmpName");
		lblEmpname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmpname.setBounds(44, 179, 96, 21);
		frame.getContentPane().add(lblEmpname);
		
		JLabel lblEmpage = new JLabel("EmpAge");
		lblEmpage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmpage.setBounds(44, 251, 96, 21);
		frame.getContentPane().add(lblEmpage);
		
		jtxtEmpName = new JTextField();
		jtxtEmpName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtEmpName.setColumns(10);
		jtxtEmpName.setBounds(321, 179, 117, 37);
		frame.getContentPane().add(jtxtEmpName);
		
		jtxtEmpAge = new JTextField();
		jtxtEmpAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtxtEmpAge.setColumns(10);
		jtxtEmpAge.setBounds(321, 251, 117, 37);
		frame.getContentPane().add(jtxtEmpAge);
		
		JButton btnexit = new JButton("EXIT");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				frame=new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame,"Confirm You Want to Exit",
						null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnexit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnexit.setBounds(518, 359, 127, 21);
		frame.getContentPane().add(btnexit);
		
		//RESET OPERATION
		
		JButton btnReset = (new JButton("Reset"));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jtxtEmpID.setText(null);
				jtxtEmpName.setText(null);
				jtxtEmpAge.setText(null);
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReset.setBounds(136, 359, 127, 21);
		frame.getContentPane().add(btnReset);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jtxtEmpID.setText(null);
				jtxtEmpName.setText(null);
				jtxtEmpAge.setText(null);
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(389, 359, 127, 21);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(262, 359, 127, 21);
		frame.getContentPane().add(btnUpdate);
	}

}
