package com.shopping.model;

import java.util.*;


import java.sql.*;


public class Product_ReportJDBCDAO implements Product_ReportDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO product_report (pro_no, mem_no, prorpt_date, prorpt_rsn, prorpt_is_cert, prorpt_unrsn) VALUES (?, ?, SYSDATE, ?, '0', ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM product_report";
	private static final String GET_ONE_STMT = "SELECT * FROM product_report where pro_no = ? and mem_no = ?";
	// 修改資料
	private static final String UPDATE = "UPDATE product_report set prorpt_date=?, prorpt_rsn=?, prorpt_is_cert=?, prorpt_unrsn=? where pro_no=? and mem_no=?";

	@Override
	public void insert(Product_ReportVO product_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT); 
			pstmt.setString(1, product_reportVO.getPro_no());
			pstmt.setString(2, product_reportVO.getMem_no());
			pstmt.setString(3, product_reportVO.getProrpt_rsn());
			pstmt.setString(4, product_reportVO.getProrpt_unrsn());

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
	public void update(Product_ReportVO product_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, product_reportVO.getProrpt_date());
			pstmt.setString(2, product_reportVO.getProrpt_rsn());
			pstmt.setString(3, product_reportVO.getProrpt_is_cert());
			pstmt.setString(4, product_reportVO.getProrpt_unrsn());
			pstmt.setString(5, product_reportVO.getPro_no());
			pstmt.setString(6, product_reportVO.getMem_no());

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
	public Product_ReportVO findByPrimaryKey(String pro_no, String mem_no) {

		Product_ReportVO product_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pro_no);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_reportVO = new Product_ReportVO();
				product_reportVO.setPro_no(rs.getString("pro_no"));
				product_reportVO.setMem_no(rs.getString("mem_no"));
				product_reportVO.setProrpt_date(rs.getTimestamp("prorpt_date"));
				product_reportVO.setProrpt_rsn(rs.getString("prorpt_rsn"));
				product_reportVO.setProrpt_is_cert(rs.getString("prorpt_is_cert"));
				product_reportVO.setProrpt_unrsn(rs.getString("prorpt_unrsn"));
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
		return product_reportVO;
	}

	@Override
	public List<Product_ReportVO> getAll() {
		List<Product_ReportVO> list = new ArrayList<Product_ReportVO>();
		Product_ReportVO product_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_reportVO = new Product_ReportVO();
				product_reportVO.setPro_no(rs.getString("pro_no"));
				product_reportVO.setMem_no(rs.getString("mem_no"));
				product_reportVO.setProrpt_date(rs.getTimestamp("prorpt_date"));
				product_reportVO.setProrpt_rsn(rs.getString("prorpt_rsn"));
				product_reportVO.setProrpt_is_cert(rs.getString("prorpt_is_cert"));
				product_reportVO.setProrpt_unrsn(rs.getString("prorpt_unrsn"));
				list.add(product_reportVO); // Store the row in the list
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

		Product_ReportJDBCDAO dao = new Product_ReportJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Product_ReportVO product_reportVO1 = new Product_ReportVO();
//		product_reportVO1.setPro_no("PRO00001");
//		product_reportVO1.setMem_no("M0000005");
//		product_reportVO1.setProrpt_rsn("不喜歡拉");
//		product_reportVO1.setProrpt_is_cert("0");
//		
//		dao.insert(product_reportVO1);

		// 修改 OK
//		Product_ReportVO product_reportVO2 = new Product_ReportVO();
//		product_reportVO2.setPro_no("PRO00001");
//		product_reportVO2.setMem_no("M0000005");
//		product_reportVO2.setProrpt_date(java.sql.Timestamp.valueOf("2016-01-01 07:22:13"));
//		product_reportVO2.setProrpt_rsn("不喜歡拉");
//		product_reportVO2.setProrpt_is_cert("0");
//		
//		dao.update(product_reportVO2);

		// 查詢複合主鍵 OK
//		Product_ReportVO product_reportVO3 = dao.findByPrimaryKey("PRO00001", "M0000001");
//		System.out.print(product_reportVO3.getPro_no() + ",");
//		System.out.print(product_reportVO3.getMem_no() + ",");
//		System.out.print(product_reportVO3.getProrpt_date() + ",");
//		System.out.println(product_reportVO3.getProrpt_rsn());
//		System.out.println("---------------------");
		
		// 查詢全部 OK
//		List<Product_ReportVO> list = dao.getAll();
//		for (Product_ReportVO product_report : list) {
//			System.out.print(product_report.getPro_no() + ",");
//			System.out.print(product_report.getMem_no()+ ",");
//			System.out.print(product_report.getProrpt_date()+ ",");
//			System.out.print(product_report.getProrpt_rsn());
//			System.out.println();
//		}
		
	}
}