import java.sql.*;
import javax.swing.*;
public class EmployeeData {
	public static Connection ConnectionDB()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite: C:\\Users\\ASUS\\eclipse-workspace\\EmployeeData\\employeee.db");
					JOptionPane.showMessageDialog(null,"Connection Made");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Connection Made");
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
