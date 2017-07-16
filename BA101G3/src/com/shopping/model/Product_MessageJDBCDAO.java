package com.shopping.model;

import java.util.*;
import java.sql.*;


public class Product_MessageJDBCDAO implements Product_MessageDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO product_message (pmsg_no, pro_no, mem_no, pmsg_date, pmsg_cnt, pmsg_is_hide) VALUES ('PMSG'||LPAD(to_char(pmsg_no_seq.NEXTVAL), 4, '0'), ?, ?, SYSDATE, ?, '0')";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM product_message";
	private static final String GET_ONE_STMT = "SELECT * FROM product_message where pmsg_no = ?";
	// 修改資料
	private static final String UPDATE = "UPDATE product_message set pro_no=?, mem_no=?, pmsg_date=?, pmsg_cnt=?, pmsg_is_hide=? where pmsg_no=?";
	@Override
	public void insert(Product_MessageVO product_messageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "pmsg_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, product_messageVO.getPro_no());
			pstmt.setString(2, product_messageVO.getMem_no());
			pstmt.setString(3, product_messageVO.getPmsg_cnt());

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
	public void update(Product_MessageVO product_messageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_messageVO.getPro_no());
			pstmt.setString(2, product_messageVO.getMem_no());
			pstmt.setTimestamp(3, product_messageVO.getPmsg_date());
			pstmt.setString(4, product_messageVO.getPmsg_cnt());
			pstmt.setString(5, product_messageVO.getPmsg_is_hide());
			pstmt.setString(6, product_messageVO.getPmsg_no());
			

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
	public Product_MessageVO findByPrimaryKey(String pmsg_no) {

		Product_MessageVO product_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pmsg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_messageVO = new Product_MessageVO();
				product_messageVO.setPmsg_no(rs.getString("pmsg_no"));
				product_messageVO.setPro_no(rs.getString("pro_no"));
				product_messageVO.setMem_no(rs.getString("mem_no"));
				product_messageVO.setPmsg_date(rs.getTimestamp("pmsg_date"));
				product_messageVO.setPmsg_cnt(rs.getString("pmsg_cnt"));
				product_messageVO.setPmsg_is_hide(rs.getString("pmsg_is_hide"));

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
		return product_messageVO;
	}

	@Override
	public List<Product_MessageVO> getAll() {
		List<Product_MessageVO> list = new ArrayList<Product_MessageVO>();
		Product_MessageVO product_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_messageVO = new Product_MessageVO();
				product_messageVO.setPmsg_no(rs.getString("pmsg_no"));
				product_messageVO.setPro_no(rs.getString("pro_no"));
				product_messageVO.setMem_no(rs.getString("mem_no"));
				product_messageVO.setPmsg_date(rs.getTimestamp("pmsg_date"));
				product_messageVO.setPmsg_cnt(rs.getString("pmsg_cnt"));
				product_messageVO.setPmsg_is_hide(rs.getString("pmsg_is_hide"));
				list.add(product_messageVO); // Store the row in the list
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

		Product_MessageJDBCDAO dao = new Product_MessageJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Product_MessageVO product_messageVO1 = new Product_MessageVO();
//		product_messageVO1.setPro_no("PRO00025");
//		product_messageVO1.setMem_no("M0000002");
//		product_messageVO1.setPmsg_cnt("品質很好，出貨速度快，讚");
//		dao.insert(product_messageVO1);

		// 修改  OK
//		Product_MessageVO product_messageVO2 = new Product_MessageVO();
//		product_messageVO2.setPmsg_no("PMSG0018");
//		product_messageVO2.setPro_no("PRO00009");
//		product_messageVO2.setMem_no("M0000002");
//		product_messageVO2.setPmsg_date(java.sql.Timestamp.valueOf("2016-1-20 03:33:33"));
//		product_messageVO2.setPmsg_cnt("還不錯拉");
//		product_messageVO2.setPmsg_is_hide("0");
//		dao.update(product_messageVO2);

		// 查詢 OK
//		Product_MessageVO product_messageVO3 = dao.findByPrimaryKey("PMSG0017");
//		System.out.print(product_messageVO3.getPmsg_no() + ",");
//		System.out.print(product_messageVO3.getMem_no() + ",");
//		System.out.println(product_messageVO3.getPmsg_cnt());
//		System.out.println("---------------------");

		// 查詢部門 OK
//		List<Product_MessageVO> list = dao.getAll();
//		for (Product_MessageVO product_message : list) {
//			System.out.print(product_message.getPmsg_no() + ",");
//			System.out.print(product_message.getMem_no()+ ",");
//			System.out.print(product_message.getPmsg_cnt());
//			System.out.println();
//		}
		
	}
}