package com.shopping.model;

import java.util.*;
import java.sql.*;


public class Seller_ReportJDBCDAO implements Seller_ReportDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO seller_report (pro_no, mem_no, sellrpt_date, sellrpt_rsn, sellrpt_is_cert, sellrpt_unrsn) VALUES (?, ?, SYSDATE, ?, '0', ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM seller_report";
	private static final String GET_ONE_STMT = "SELECT * FROM seller_report where pro_no = ? and mem_no = ?";
	// 修改資料
	private static final String UPDATE = "UPDATE seller_report set sellrpt_date=?, sellrpt_rsn=?, sellrpt_is_cert=?, sellrpt_unrsn=? where pro_no=? and mem_no=?";
	@Override
	public void insert(Seller_ReportVO seller_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "pro_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, seller_reportVO.getPro_no());
			pstmt.setString(2, seller_reportVO.getMem_no());
			pstmt.setString(3, seller_reportVO.getSellrpt_rsn());
			pstmt.setString(4, seller_reportVO.getSellrpt_unrsn());

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
	public void update(Seller_ReportVO seller_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, seller_reportVO.getSellrpt_date());
			pstmt.setString(2, seller_reportVO.getSellrpt_rsn());
			pstmt.setString(3, seller_reportVO.getSellrpt_is_cert());
			pstmt.setString(4, seller_reportVO.getSellrpt_unrsn());
			pstmt.setString(5, seller_reportVO.getPro_no());
			pstmt.setString(6, seller_reportVO.getMem_no());

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
	public Seller_ReportVO findByPrimaryKey(String pro_no, String mem_no) {

		Seller_ReportVO seller_reportVO = null;
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
				seller_reportVO = new Seller_ReportVO();
				seller_reportVO.setPro_no(rs.getString("pro_no"));
				seller_reportVO.setMem_no(rs.getString("mem_no"));
				seller_reportVO.setSellrpt_date(rs.getTimestamp("sellrpt_date"));
				seller_reportVO.setSellrpt_rsn(rs.getString("sellrpt_rsn"));
				seller_reportVO.setSellrpt_is_cert(rs.getString("sellrpt_is_cert"));
				seller_reportVO.setSellrpt_unrsn(rs.getString("sellrpt_unrsn"));
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
		return seller_reportVO;
	}

	@Override
	public List<Seller_ReportVO> getAll() {
		List<Seller_ReportVO> list = new ArrayList<Seller_ReportVO>();
		Seller_ReportVO seller_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				seller_reportVO = new Seller_ReportVO();
				seller_reportVO.setPro_no(rs.getString("pro_no"));
				seller_reportVO.setMem_no(rs.getString("mem_no"));
				seller_reportVO.setSellrpt_date(rs.getTimestamp("sellrpt_date"));
				seller_reportVO.setSellrpt_rsn(rs.getString("sellrpt_rsn"));
				seller_reportVO.setSellrpt_is_cert(rs.getString("sellrpt_is_cert"));
				seller_reportVO.setSellrpt_unrsn(rs.getString("sellrpt_unrsn"));
				list.add(seller_reportVO); // Store the row in the list
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

		Seller_ReportJDBCDAO dao = new Seller_ReportJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Seller_ReportVO seller_reportVO1 = new Seller_ReportVO();
//		seller_reportVO1.setPro_no("PRO00009");
//		seller_reportVO1.setMem_no("M0000001");
//		seller_reportVO1.setSellrpt_rsn("態度很差欸");
//		seller_reportVO1.setSellrpt_unrsn("過拉過拉");
//		dao.insert(seller_reportVO1);

		// 修改 OK
//		Seller_ReportVO seller_reportVO2 = new Seller_ReportVO();
//		seller_reportVO2.setPro_no("PRO00007");
//		seller_reportVO2.setMem_no("M0000001");
//		seller_reportVO2.setSellrpt_date(java.sql.Timestamp.valueOf("2016-1-22 21:33:33"));
//		seller_reportVO2.setSellrpt_rsn("態度很差欸");
//		seller_reportVO2.setSellrpt_is_cert("1");
//		seller_reportVO2.setSellrpt_unrsn("過拉過拉");
//		dao.update(seller_reportVO2);

		// 查詢
//		Seller_ReportVO seller_reportVO3 = dao.findByPrimaryKey("PRO00007", "M0000001");
//		System.out.print(seller_reportVO3.getPro_no() + ",");
//		System.out.print(seller_reportVO3.getMem_no() + ",");
//		System.out.println(seller_reportVO3.getSellrpt_rsn());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<Seller_ReportVO> list = dao.getAll();
//		for (Seller_ReportVO seller_report : list) {
//			System.out.print(seller_report.getPro_no() + ",");
//			System.out.print(seller_report.getMem_no()+ ",");
//			System.out.print(seller_report.getSellrpt_rsn());
//			System.out.println();
//		}
		
	}
}