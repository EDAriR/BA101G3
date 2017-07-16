package com.shopping.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class OrderlistDAO implements OrderlistDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA101G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			
			con = ds.getConnection();
			String[] cols = { "ord_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			
			pstmt.setString(1, orderlistVO.getPro_no());
			pstmt.setString(2, orderlistVO.getMem_no());
			pstmt.setInt(3, orderlistVO.getOrd_amt());
			pstmt.setTimestamp(4, orderlistVO.getOrd_startd());
			pstmt.setTimestamp(5, orderlistVO.getOrd_endd());
			pstmt.setTimestamp(6, orderlistVO.getOrd_payd());
			pstmt.setString(7, orderlistVO.getOrd_getadd());
			pstmt.setString(8, orderlistVO.getOrd_no());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
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


}
