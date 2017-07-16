package com.baby.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class Growing_IntervalDAO implements Growing_IntervalDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
		} catch(NamingException e){
			e.printStackTrace();
		}
	}
	
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

			String[] cols = { "gint_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setInt(1, growing_intervalVO.getGint_int());

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
	public void update(Growing_IntervalVO growing_intervalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, growing_intervalVO.getGint_int());
			pstmt.setString(2, growing_intervalVO.getGint_no());

			pstmt.executeUpdate();

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

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gint_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				growing_intervalVO = new Growing_IntervalVO();
				growing_intervalVO.setGint_no(rs.getString("gint_no"));
				growing_intervalVO.setGint_int(rs.getInt("gint_int"));
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

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				growing_intervalVO = new Growing_IntervalVO();
				growing_intervalVO.setGint_no(rs.getString("gint_no"));
				growing_intervalVO.setGint_int(rs.getInt("gint_int"));
				list.add(growing_intervalVO); // Store the row in the list
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