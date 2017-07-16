package utility;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class Map_MechanismReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		req.setCharacterEncoding("Big5");
		String map_no = req.getParameter("map_no");
		String map_no2 = new String(map_no.getBytes("ISO-8859-1"), "Big5");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT map_photo FROM map_mechanism WHERE map_no='" + map_no2 + "'");
			
			// TODO 判斷沒有圖片時，不會顯示
			
			if (rs.next()) { 
				if(rs.getBytes("map_photo") != null) {
					BufferedInputStream in = new BufferedInputStream (new ByteArrayInputStream(rs.getBytes("map_photo")));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				}
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
