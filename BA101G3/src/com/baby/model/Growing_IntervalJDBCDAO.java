package com.baby.model;

import java.util.*;

import java.sql.*;


public class Growing_IntervalJDBCDAO implements Growing_IntervalDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO growing_interval (gint_no, gint_int) VALUES (LPAD(to_char(gint_no_seq.NEXTVAL), 2, '0'), ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM growing_interval";
	private static final String GET_ONE_STMT = "SELECT * FROM growing_interval WHERE gint_no = ?";
	// 修改資料
	private static final String UPDATE_STMT = "UPDATE growing_interval set gint_int=? WHERE gint_no=?";

	
	@Override
	public void insert(Growing_IntervalVO growing_intervalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "gint_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setInt(1, growing_intervalVO.getGint_int());

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
	public void update(Growing_IntervalVO growing_intervalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, growing_intervalVO.getGint_int());
			pstmt.setString(2, growing_intervalVO.getGint_no());

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
	public Growing_IntervalVO findByPrimaryKey(String gint_no) {

		Growing_IntervalVO growing_intervalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gint_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				growing_intervalVO = new Growing_IntervalVO();
				growing_intervalVO.setGint_no(rs.getString("gint_no"));
				growing_intervalVO.setGint_int(rs.getInt("gint_int"));
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
		return growing_intervalVO;
	}

	@Override
	public List<Growing_IntervalVO> getAll() {
		List<Growing_IntervalVO> list = new ArrayList<Growing_IntervalVO>();
		Growing_IntervalVO growing_intervalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				growing_intervalVO = new Growing_IntervalVO();
				growing_intervalVO.setGint_no(rs.getString("gint_no"));
				growing_intervalVO.setGint_int(rs.getInt("gint_int"));
				list.add(growing_intervalVO); // Store the row in the list
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

		Growing_IntervalJDBCDAO dao = new Growing_IntervalJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Growing_IntervalVO growing_intervalVO1 = new Growing_IntervalVO();
//		growing_intervalVO1.setGint_int(7000);
//		dao.insert(growing_intervalVO1);
		
		// 修改 OK
//		Growing_IntervalVO growing_intervalVO2 = new Growing_IntervalVO();
//		growing_intervalVO2.setGint_no("17");
//		growing_intervalVO2.setGint_int(6000);
//		dao.update(growing_intervalVO2);

		// 查詢 OK
//		Growing_IntervalVO growing_intervalVO3 = dao.findByPrimaryKey("10");
//		System.out.print(growing_intervalVO3.getGint_no() + ",");
//		System.out.println(growing_intervalVO3.getGint_int());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<Growing_IntervalVO> list = dao.getAll();
//		for (Growing_IntervalVO growing_intervalVO : list) {
//			System.out.print(growing_intervalVO.getGint_no() + ",");
//			System.out.print(growing_intervalVO.getGint_int());
//			System.out.println();
//		}
		
	}
}