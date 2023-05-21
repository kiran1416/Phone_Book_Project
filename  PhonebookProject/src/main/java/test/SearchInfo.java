package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchlink")
public class SearchInfo extends HttpServlet{
	Connection con;
	@Override
	public void init() throws ServletException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1ejm9","root","sql123");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
		String phone=req.getParameter("phone");
		
		PreparedStatement pstmt;
		ResultSet rs;
		PrintWriter pw=resp.getWriter();
		
		String query1="select * from phonebook_info where name=? or phone=?";
		
		try {
			pstmt=con.prepareStatement(query1);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs=pstmt.executeQuery();
			pw.print("<table border='2' rules='all' cellpadding='10px' style='margin-top:20%;margin-left:40%;'>");
			pw.print("<tr>");
			pw.print("<th>ID</th>");
			pw.print("<th>NAME</th>");
			pw.print("<th>PHONE NUMBER</th>");
			pw.print("</tr>");
			
			while(rs.next()) {
				pw.print("<tr>");
				pw.print("<td>"+rs.getInt(1)+"</td>");
				pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getString(3)+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
