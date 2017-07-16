package com.baby.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class Growing_ControlDAO implements Growing_ControlDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO growing_control (ctrl_gen, gint_no, ctrl_hc, ctrl_ht, ctrl_wt, ctrl_diet, ctrl_sleep) VALUES (?, ?, ?, ?, ?, ?, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM growing_control";
	private static final String GET_ONE_STMT = "SELECT * FROM growing_control WHERE ctrl_gen=? AND gint_no = ?";
	// 修改資料
	private static final String UPDATE_STMT = "UPDATE growing_control set ctrl_hc=?, ctrl_ht=?, ctrl_wt=?, ctrl_diet=?, ctrl_sleep=? WHERE ctrl_gen=? AND gint_no=?";

	
	@Override
	public void insert(Growing_ControlVO growing_controlVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, growing_controlVO.getCtrl_gen());
			pstmt.setString(2, growing_controlVO.getGint_no());
			pstmt.setDouble(3, growing_controlVO.getCtrl_hc());
			pstmt.setDouble(4, growing_controlVO.getCtrl_ht());
			pstmt.setDouble(5, growing_controlVO.getCtrl_wt());
			pstmt.setDouble(6, growing_controlVO.getCtrl_diet());
			pstmt.setDouble(7, growing_controlVO.getCtrl_sleep());

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
	public void update(Growing_ControlVO growing_controlVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setDouble(1, growing_controlVO.getCtrl_hc());
			pstmt.setDouble(2, growing_controlVO.getCtrl_ht());
			pstmt.setDouble(3, growing_controlVO.getCtrl_wt());
			pstmt.setDouble(4, growing_controlVO.getCtrl_diet());
			pstmt.setDouble(5, growing_controlVO.getCtrl_sleep());
			pstmt.setString(6, growing_controlVO.getCtrl_gen());
			pstmt.setString(7, growing_controlVO.getGint_no());

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
	public Growing_ControlVO findByPrimaryKey(String ctrl_gen, String gint_no) {

		Growing_ControlVO growing_controlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

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