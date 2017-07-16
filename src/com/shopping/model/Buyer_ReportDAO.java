package com.shopping.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Buyer_ReportDAO implements Buyer_ReportDAO_interface{
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
	private static final String INSERT_STMT = "INSERT INTO buyer_report (ord_no, mem_no, buyrpt_date, buyrpt_rsn, buyrpt_is_cert, buyrpt_unrsn) VALUES (?, ?, SYSDATE, ?, '0', ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM buyer_report";
	private static final String GET_ONE_STMT = "SELECT * FROM buyer_report WHERE ord_no = ? AND mem_no = ?";
	// 修改資料
	private static final String UPDATE = "UPDATE buyer_report set buyrpt_date=?, buyrpt_rsn=?, buyrpt_is_cert=?, buyrpt_unrsn=? where ord_no=? AND mem_no=?";
	
		
	@Override
	public void insert(Buyer_ReportVO buyer_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();			
			String[] cols = { "ord_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, buyer_reportVO.getOrd_no());
			pstmt.setString(2, buyer_reportVO.getMem_no());
			pstmt.setString(3, buyer_reportVO.getBuyrpt_rsn());
			pstmt.setString(4, buyer_reportVO.getBuyrpt_unrsn());

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
	public void update(Buyer_ReportVO buyer_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, buyer_reportVO.getBuyrpt_date());
			pstmt.setString(2, buyer_reportVO.getBuyrpt_rsn());
			pstmt.setString(3, buyer_reportVO.getBuyrpt_is_cert());
			pstmt.setString(4, buyer_reportVO.getBuyrpt_unrsn());
			pstmt.setString(5, buyer_reportVO.getOrd_no());
			pstmt.setString(6, buyer_reportVO.getMem_no());

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
	public Buyer_ReportVO findByPrimaryKey(String ord_no, String mem_no) {
		Buyer_ReportVO buyer_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ord_no);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				buyer_reportVO = new Buyer_ReportVO();
				buyer_reportVO.setOrd_no(rs.getString("ord_no"));
				buyer_reportVO.setMem_no(rs.getString("mem_no"));
				buyer_reportVO.setBuyrpt_date(rs.getTimestamp("buyrpt_date"));
				buyer_reportVO.setBuyrpt_rsn(rs.getString("buyrpt_rsn"));
				buyer_reportVO.setBuyrpt_is_cert(rs.getString("buyrpt_is_cert"));
				buyer_reportVO.setBuyrpt_unrsn(rs.getString("buyrpt_unrsn"));
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
		return buyer_reportVO;
	}

	@Override
	public List<Buyer_ReportVO> getAll() {
		List<Buyer_ReportVO> list = new ArrayList<Buyer_ReportVO>();
		Buyer_ReportVO buyer_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				buyer_reportVO = new Buyer_ReportVO();
				buyer_reportVO.setOrd_no(rs.getString("ord_no"));
				buyer_reportVO.setMem_no(rs.getString("mem_no"));
				buyer_reportVO.setBuyrpt_date(rs.getTimestamp("buyrpt_date"));
				buyer_reportVO.setBuyrpt_rsn(rs.getString("buyrpt_rsn"));
				buyer_reportVO.setBuyrpt_is_cert(rs.getString("buyrpt_is_cert"));
				buyer_reportVO.setBuyrpt_unrsn(rs.getString("buyrpt_unrsn"));
				list.add(buyer_reportVO); // Store the row in the list
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