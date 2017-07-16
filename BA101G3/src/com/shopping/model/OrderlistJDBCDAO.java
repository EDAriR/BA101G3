package com.shopping.model;

import java.util.*;
import java.sql.*;


public class OrderlistJDBCDAO implements OrderlistDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO orderlist (ord_no, pro_no, mem_no, ord_amt, ord_startd, ord_endd, ord_payd, ord_getadd) VALUES ('ORD'||LPAD(to_char(ord_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, SYSDATE, ?, ?, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM orderlist";
	private static final String GET_ONE_STMT = "SELECT * FROM orderlist WHERE ord_no = ?";
	// 修改資料
	private static final String UPDATE = "UPDATE orderlist set pro_no=?, mem_no=?, ord_amt=?, ord_startd=?, ord_endd=?, ord_payd=?, ord_getadd=? WHERE ord_no=?";
	@Override
	public void insert(OrderlistVO orderlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "ord_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, orderlistVO.getPro_no());
			pstmt.setString(2, orderlistVO.getMem_no());
			pstmt.setInt(3, orderlistVO.getOrd_amt());
			pstmt.setTimestamp(4, orderlistVO.getOrd_endd());
			pstmt.setTimestamp(5, orderlistVO.getOrd_payd());
			pstmt.setString(6, orderlistVO.getOrd_getadd());

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
	public void update(OrderlistVO orderlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderlistVO.getPro_no());
			pstmt.setString(2, orderlistVO.getMem_no());
			pstmt.setInt(3, orderlistVO.getOrd_amt());
			pstmt.setTimestamp(4, orderlistVO.getOrd_startd());
			pstmt.setTimestamp(5, orderlistVO.getOrd_endd());
			pstmt.setTimestamp(6, orderlistVO.getOrd_payd());
			pstmt.setString(7, orderlistVO.getOrd_getadd());
			pstmt.setString(8, orderlistVO.getOrd_no());

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
	public OrderlistVO findByPrimaryKey(String ord_no) {

		OrderlistVO orderlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderlistVO = new OrderlistVO();
				orderlistVO.setOrd_no(rs.getString("ord_no"));
				orderlistVO.setPro_no(rs.getString("pro_no"));
				orderlistVO.setMem_no(rs.getString("mem_no"));
				orderlistVO.setOrd_amt(rs.getInt("ord_amt"));
				orderlistVO.setOrd_startd(rs.getTimestamp("ord_startd"));
				orderlistVO.setOrd_endd(rs.getTimestamp("ord_endd"));
				orderlistVO.setOrd_payd(rs.getTimestamp("ord_payd"));
				orderlistVO.setOrd_getadd(rs.getString("ord_getadd"));
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
		return orderlistVO;
	}

	@Override
	public List<OrderlistVO> getAll() {
		List<OrderlistVO> list = new ArrayList<OrderlistVO>();
		OrderlistVO orderlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderlistVO = new OrderlistVO();
				orderlistVO.setOrd_no(rs.getString("ord_no"));
				orderlistVO.setPro_no(rs.getString("pro_no"));
				orderlistVO.setMem_no(rs.getString("mem_no"));
				orderlistVO.setOrd_amt(rs.getInt("ord_amt"));
				orderlistVO.setOrd_startd(rs.getTimestamp("ord_startd"));
				orderlistVO.setOrd_endd(rs.getTimestamp("ord_endd"));
				orderlistVO.setOrd_payd(rs.getTimestamp("ord_payd"));
				orderlistVO.setOrd_getadd(rs.getString("ord_getadd"));
				list.add(orderlistVO); // Store the row in the list
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

		OrderlistJDBCDAO dao = new OrderlistJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		OrderlistVO orderlistVO1 = new OrderlistVO();
//		orderlistVO1.setPro_no("PRO00007");
//		orderlistVO1.setMem_no("M0000003");
//		orderlistVO1.setOrd_amt(678);
//		orderlistVO1.setOrd_getadd("桃園trytry");
//		dao.insert(orderlistVO1);

		// 修改 OK
//		OrderlistVO orderlistVO2 = new OrderlistVO();
//		orderlistVO2.setOrd_no("ORD00001");
//		orderlistVO2.setPro_no("PRO00007");
//		orderlistVO2.setMem_no("M0000003");
//		orderlistVO2.setOrd_amt(578);
//		orderlistVO2.setOrd_startd(java.sql.Timestamp.valueOf("2016-1-22 04:55:33"));
//		orderlistVO2.setOrd_endd(java.sql.Timestamp.valueOf("2016-1-23 04:55:33"));
//		orderlistVO2.setOrd_getadd("台北市好了");
//		dao.update(orderlistVO2);

		// 查詢 OK
//		OrderlistVO orderlistVO3 = dao.findByPrimaryKey("ORD00001");
//		System.out.print(orderlistVO3.getOrd_no() + ",");
//		System.out.print(orderlistVO3.getOrd_amt() + ",");
//		System.out.println(orderlistVO3.getOrd_startd());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<OrderlistVO> list = dao.getAll();
//		for (OrderlistVO orderlist : list) {
//			System.out.print(orderlist.getOrd_no() + ",");
//			System.out.print(orderlist.getOrd_amt()+ ",");
//			System.out.print(orderlist.getOrd_startd());
//			System.out.println();
//		}
		
	}
}