package com.baby.model;

import java.util.*;

import java.sql.*;


public class Growing_ControlJDBCDAO implements Growing_ControlDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 穝糤戈
	private static final String INSERT_STMT = "INSERT INTO growing_control (ctrl_gen, gint_no, ctrl_hc, ctrl_ht, ctrl_wt, ctrl_diet, ctrl_sleep) VALUES (?, ?, ?, ?, ?, ?, ?)";
	// 琩高戈
	private static final String GET_ALL_STMT = "SELECT * FROM growing_control";
	private static final String GET_ONE_STMT = "SELECT * FROM growing_control WHERE ctrl_gen=? AND gint_no = ?";
	// э戈
	private static final String UPDATE_STMT = "UPDATE growing_control set ctrl_hc=?, ctrl_ht=?, ctrl_wt=?, ctrl_diet=?, ctrl_sleep=? WHERE ctrl_gen=? AND gint_no=?";

	
	@Override
	public void insert(Growing_ControlVO growing_controlVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, growing_controlVO.getCtrl_gen());
			pstmt.setString(2, growing_controlVO.getGint_no());
			pstmt.setDouble(3, growing_controlVO.getCtrl_hc());
			pstmt.setDouble(4, growing_controlVO.getCtrl_ht());
			pstmt.setDouble(5, growing_controlVO.getCtrl_wt());
			pstmt.setDouble(6, growing_controlVO.getCtrl_diet());
			pstmt.setDouble(7, growing_controlVO.getCtrl_sleep());

			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Growing_ControlVO growing_controlVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setDouble(1, growing_controlVO.getCtrl_hc());
			pstmt.setDouble(2, growing_controlVO.getCtrl_ht());
			pstmt.setDouble(3, growing_controlVO.getCtrl_wt());
			pstmt.setDouble(4, growing_controlVO.getCtrl_diet());
			pstmt.setDouble(5, growing_controlVO.getCtrl_sleep());
			pstmt.setString(6, growing_controlVO.getCtrl_gen());
			pstmt.setString(7, growing_controlVO.getGint_no());

			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	

	@Override
	public Growing_ControlVO findByPrimaryKey(String ctrl_gen, String gint_no) {

		Growing_ControlVO growing_controlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ctrl_gen);
			pstmt.setString(2, gint_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				growing_controlVO = new Growing_ControlVO();
				growing_controlVO.setCtrl_gen(rs.getString("ctrl_gen"));
				growing_controlVO.setGint_no(rs.getString("gint_no"));
				growing_controlVO.setCtrl_hc(rs.getDouble("ctrl_hc"));
				growing_controlVO.setCtrl_ht(rs.getDouble("ctrl_ht"));
				growing_controlVO.setCtrl_wt(rs.getDouble("ctrl_wt"));
				growing_controlVO.setCtrl_diet(rs.getDouble("ctrl_diet"));
				growing_controlVO.setCtrl_sleep(rs.getDouble("ctrl_sleep"));
				
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return growing_controlVO;
	}

	@Override
	public List<Growing_ControlVO> getAll() {
		List<Growing_ControlVO> list = new ArrayList<Growing_ControlVO>();
		Growing_ControlVO growing_controlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				growing_controlVO = new Growing_ControlVO();
				growing_controlVO.setCtrl_gen(rs.getString("ctrl_gen"));
				growing_controlVO.setGint_no(rs.getString("gint_no"));
				growing_controlVO.setCtrl_hc(rs.getDouble("ctrl_hc"));
				growing_controlVO.setCtrl_ht(rs.getDouble("ctrl_ht"));
				growing_controlVO.setCtrl_wt(rs.getDouble("ctrl_wt"));
				growing_controlVO.setCtrl_diet(rs.getDouble("ctrl_diet"));
				growing_controlVO.setCtrl_sleep(rs.getDouble("ctrl_sleep"));
				list.add(growing_controlVO); // Store the row in the list
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


	public static void main(String[] args) {

		Growing_ControlJDBCDAO dao = new Growing_ControlJDBCDAO();
		// 代刚–琌ㄏノ
		// 穝糤 OK
//		Growing_ControlVO growing_controlVO1 = new Growing_ControlVO();
//		growing_controlVO1.setCtrl_gen("1");
//		growing_controlVO1.setGint_no("17");
//		growing_controlVO1.setCtrl_hc(50.5);
//		growing_controlVO1.setCtrl_ht(100.4);
//		growing_controlVO1.setCtrl_wt(20.8);
//		growing_controlVO1.setCtrl_diet(20.8);
//		growing_controlVO1.setCtrl_sleep(8.6);
//		dao.insert(growing_controlVO1);
		
		// э OK
//		Growing_ControlVO growing_controlVO2 = new Growing_ControlVO();
//		growing_controlVO2.setCtrl_gen("1");
//		growing_controlVO2.setGint_no("17");
//		growing_controlVO2.setCtrl_hc(50.5);
//		growing_controlVO2.setCtrl_ht(500.4);
//		growing_controlVO2.setCtrl_wt(20.8);
//		growing_controlVO2.setCtrl_diet(20.8);
//		growing_controlVO2.setCtrl_sleep(8.6);
//		dao.update(growing_controlVO2);

		// 琩高 OK
//		Growing_ControlVO growing_controlVO3 = dao.findByPrimaryKey("1", "07");
//		System.out.print(growing_controlVO3.getCtrl_gen() + ",");
//		System.out.print(growing_controlVO3.getGint_no() + ",");
//		System.out.print(growing_controlVO3.getCtrl_hc() + ",");
//		System.out.print(growing_controlVO3.getCtrl_ht() + ",");
//		System.out.print(growing_controlVO3.getCtrl_wt() + ",");
//		System.out.print(growing_controlVO3.getCtrl_diet() + ",");
//		System.out.println(growing_controlVO3.getCtrl_sleep());
//		System.out.println("---------------------");

		// 琩高场 OK
//		List<Growing_ControlVO> list = dao.getAll();
//		for (Growing_ControlVO growing_controlVO : list) {
//			System.out.print(growing_controlVO.getCtrl_gen() + ",");
//			System.out.print(growing_controlVO.getGint_no() + ",");
//			System.out.print(growing_controlVO.getCtrl_hc() + ",");
//			System.out.print(growing_controlVO.getCtrl_ht() + ",");
//			System.out.print(growing_controlVO.getCtrl_wt() + ",");
//			System.out.print(growing_controlVO.getCtrl_diet() + ",");
//			System.out.print(growing_controlVO.getCtrl_sleep());
//			System.out.println();
//		}
		
	}
}