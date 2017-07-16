package com.baby.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class No_Staple_FoodDAO implements No_Staple_FoodDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO no_staple_food (nf_no, gint_no, nf_title, nf_cnt) VALUES ('NF'||LPAD(to_char(nf_no_seq.NEXTVAL), 6, '0'), ?, ?, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM no_staple_food";
	private static final String GET_ONE_STMT = "SELECT * FROM no_staple_food WHERE nf_no = ?";
	// 修改資料
	private static final String UPDATE_STMT = "UPDATE no_staple_food set gint_no=?, nf_title=?, nf_cnt=? WHERE nf_no=?";
	// 刪除資料
	private static final String DELETE_NO_STAPLE_FOOD = "DELETE FROM no_staple_food WHERE nf_no = ?";

	
	@Override
	public void insert(No_Staple_FoodVO no_staple_foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String[] cols = { "nf_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			
			pstmt.setString(1, no_staple_foodVO.getGint_no());
			pstmt.setString(2, no_staple_foodVO.getNf_title());
			pstmt.setString(3, no_staple_foodVO.getNf_cnt());

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
	public void update(No_Staple_FoodVO no_staple_foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, no_staple_foodVO.getGint_no());
			pstmt.setString(2, no_staple_foodVO.getNf_title());
			pstmt.setString(3, no_staple_foodVO.getNf_cnt());
			pstmt.setString(4, no_staple_foodVO.getNf_no());

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
	public void delete(String nf_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_NO_STAPLE_FOOD);
			pstmt.setString(1, nf_no);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除副食品" + nf_no);

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public No_Staple_FoodVO findByPrimaryKey(String nf_no) {

		No_Staple_FoodVO no_staple_foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, nf_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				no_staple_foodVO = new No_Staple_FoodVO();
				no_staple_foodVO.setNf_no(rs.getString("nf_no"));
				no_staple_foodVO.setGint_no(rs.getString("gint_no"));
				no_staple_foodVO.setNf_title(rs.getString("nf_title"));
				no_staple_foodVO.setNf_cnt(rs.getString("nf_cnt"));
				
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
		return no_staple_foodVO;
	}

	@Override
	public List<No_Staple_FoodVO> getAll() {
		List<No_Staple_FoodVO> list = new ArrayList<No_Staple_FoodVO>();
		No_Staple_FoodVO no_staple_foodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				no_staple_foodVO = new No_Staple_FoodVO();
				no_staple_foodVO.setNf_no(rs.getString("nf_no"));
				no_staple_foodVO.setGint_no(rs.getString("gint_no"));
				no_staple_foodVO.setNf_title(rs.getString("nf_title"));
				no_staple_foodVO.setNf_cnt(rs.getString("nf_cnt"));
				list.add(no_staple_foodVO); // Store the row in the list
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