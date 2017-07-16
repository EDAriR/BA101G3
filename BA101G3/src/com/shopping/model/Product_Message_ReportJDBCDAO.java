package com.shopping.model;

import java.util.*;
import java.sql.*;


public class Product_Message_ReportJDBCDAO implements Product_Message_ReportDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO product_message_report (pmsg_no, mem_no, pmrpt_date, pmrpt_rsn, pmrpt_is_cert, pmrpt_unrsn) VALUES (?, ?, SYSDATE, ?, '0', ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM product_message_report";
	private static final String GET_ONE_STMT = "SELECT * FROM product_message_report WHERE pmsg_no = ? AND mem_no = ?";
	// 刪除資料
	private static final String DELETE_PRORPT = "DELETE FROM product_message_report WHERE pmsg_no = ? AND mem_no = ?";	
	// 修改資料
	private static final String UPDATE = "UPDATE product_message_report SET pmrpt_date=?, pmrpt_rsn=?, pmrpt_is_cert=?, pmrpt_unrsn=? where pmsg_no=? AND mem_no=?";
	@Override
	public void insert(Product_Message_ReportVO product_message_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, product_message_reportVO.getPmsg_no());
			pstmt.setString(2, product_message_reportVO.getMem_no());
			pstmt.setString(3, product_message_reportVO.getPmrpt_rsn());
			pstmt.setString(4, product_message_reportVO.getPmrpt_unrsn());

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
	public void update(Product_Message_ReportVO product_message_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, product_message_reportVO.getPmrpt_date());
			pstmt.setString(2, product_message_reportVO.getPmrpt_rsn());
			pstmt.setString(3, product_message_reportVO.getPmrpt_is_cert());
			pstmt.setString(4, product_message_reportVO.getPmrpt_unrsn());
			pstmt.setString(5, product_message_reportVO.getPmsg_no());
			pstmt.setString(6, product_message_reportVO.getMem_no());

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
	public Product_Message_ReportVO findByPrimaryKey(String pmsg_no, String mem_no) {

		Product_Message_ReportVO product_message_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pmsg_no);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_message_reportVO = new Product_Message_ReportVO();
				product_message_reportVO.setPmsg_no(rs.getString("pmsg_no"));
				product_message_reportVO.setMem_no(rs.getString("mem_no"));
				product_message_reportVO.setPmrpt_date(rs.getTimestamp("pmrpt_date"));
				product_message_reportVO.setPmrpt_rsn(rs.getString("pmrpt_rsn"));
				product_message_reportVO.setPmrpt_is_cert(rs.getString("pmrpt_is_cert"));
				product_message_reportVO.setPmrpt_unrsn(rs.getString("pmrpt_unrsn"));
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
		return product_message_reportVO;
	}

	@Override
	public List<Product_Message_ReportVO> getAll() {
		List<Product_Message_ReportVO> list = new ArrayList<Product_Message_ReportVO>();
		Product_Message_ReportVO product_message_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_message_reportVO = new Product_Message_ReportVO();
				product_message_reportVO.setPmsg_no(rs.getString("pmsg_no"));
				product_message_reportVO.setMem_no(rs.getString("mem_no"));
				product_message_reportVO.setPmrpt_date(rs.getTimestamp("pmrpt_date"));
				product_message_reportVO.setPmrpt_rsn(rs.getString("pmrpt_rsn"));
				product_message_reportVO.setPmrpt_is_cert(rs.getString("pmrpt_is_cert"));
				product_message_reportVO.setPmrpt_unrsn(rs.getString("pmrpt_unrsn"));
				list.add(product_message_reportVO); // Store the row in the list
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

		Product_Message_ReportJDBCDAO dao = new Product_Message_ReportJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Product_Message_ReportVO product_message_reportVO1 = new Product_Message_ReportVO();
//		product_message_reportVO1.setPmsg_no("PMSG0020");
//		product_message_reportVO1.setMem_no("M0000001");
//		product_message_reportVO1.setPmrpt_rsn("內容怪怪的");
//		dao.insert(product_message_reportVO1);

		// 修改 OK
//		Product_Message_ReportVO product_message_reportVO2 = new Product_Message_ReportVO();
//		product_message_reportVO2.setPmsg_no("PMSG0018");
//		product_message_reportVO2.setMem_no("M0000001");
//		product_message_reportVO2.setPmrpt_date(java.sql.Timestamp.valueOf("2016-3-17 07:23:00"));
//		product_message_reportVO2.setPmrpt_rsn("內容怪怪的");
//		product_message_reportVO2.setPmrpt_is_cert("2");
//		product_message_reportVO2.setPmrpt_unrsn("好啊");
//		dao.update(product_message_reportVO2);

		// 查詢 OK
//		Product_Message_ReportVO product_message_reportVO3 = dao.findByPrimaryKey("PMSG0018", "M0000001");
//		System.out.print(product_message_reportVO3.getPmsg_no() + ",");
//		System.out.print(product_message_reportVO3.getMem_no() + ",");
//		System.out.println(product_message_reportVO3.getPmrpt_rsn());
//		System.out.println("---------------------");

		// 查詢部門 OK
//		List<Product_Message_ReportVO> list = dao.getAll();
//		for (Product_Message_ReportVO product_message_report : list) {
//			System.out.print(product_message_report.getPmsg_no() + ",");
//			System.out.print(product_message_report.getMem_no()+ ",");
//			System.out.print(product_message_report.getPmrpt_rsn());
//			System.out.println();
//		}
		
	}
}