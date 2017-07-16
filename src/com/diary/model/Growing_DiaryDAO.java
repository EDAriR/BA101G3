package com.diary.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Growing_DiaryDAO implements Growing_DiaryDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO growing_diary (gd_no, baby_no, gd_title, gd_date, gd_cnt, gd_shr) VALUES ('GD'||LPAD(to_char(gd_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM growing_diary ORDER BY gd_no DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM growing_diary WHERE gd_no = ?";
	private static final String UPDATE_STMT = "UPDATE growing_diary SET gd_title=? , gd_date=?, gd_cnt=?, gd_shr=? WHERE gd_no= ?";
	private static final String DELETE_GROWING_DIARY = "DELETE FROM growing_diary WHERE gd_no= ?";

	@Override
	public void insert(Growing_DiaryVO growing_diaryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String[] cols = { "gd_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			
			pstmt.setString(1, growing_diaryVO.getBaby_no());
			pstmt.setString(2, growing_diaryVO.getGd_title());
			pstmt.setTimestamp(3, growing_diaryVO.getGd_date());
			pstmt.setString(4, growing_diaryVO.getGd_cnt());
			pstmt.setString(5, growing_diaryVO.getGd_shr());

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
	public void update(Growing_DiaryVO growing_diaryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, growing_diaryVO.getGd_title());
			pstmt.setTimestamp(2, growing_diaryVO.getGd_date());
			pstmt.setString(3, growing_diaryVO.getGd_cnt());
			pstmt.setString(4, growing_diaryVO.getGd_shr());
			pstmt.setString(5, growing_diaryVO.getGd_no());

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
	public void delete(String gd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_GROWING_DIARY);

			pstmt.setString(1, gd_no);

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
	public Growing_DiaryVO findByPrimaryKey(String gd_no) {
		Growing_DiaryVO growing_diaryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				growing_diaryVO = new Growing_DiaryVO();
				growing_diaryVO.setGd_no(rs.getString("gd_no"));
				growing_diaryVO.setBaby_no(rs.getString("baby_no"));
				growing_diaryVO.setGd_title(rs.getString("gd_title"));
				growing_diaryVO.setGd_date(rs.getTimestamp("gd_date"));
				growing_diaryVO.setGd_cnt(rs.getString("gd_cnt"));
				growing_diaryVO.setGd_shr(rs.getString("gd_shr"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return growing_diaryVO;
	}

	@Override
	public List<Growing_DiaryVO> getAll() {
		List<Growing_DiaryVO> list = new ArrayList<Growing_DiaryVO>();
		Growing_DiaryVO growing_diaryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				growing_diaryVO = new Growing_DiaryVO();
				growing_diaryVO.setGd_no(rs.getString("gd_no"));
				growing_diaryVO.setBaby_no(rs.getString("baby_no"));
				growing_diaryVO.setGd_title(rs.getString("gd_title"));
				growing_diaryVO.setGd_date(rs.getTimestamp("gd_date"));
				growing_diaryVO.setGd_cnt(rs.getString("gd_cnt"));
				growing_diaryVO.setGd_shr(rs.getString("gd_shr"));
				list.add(growing_diaryVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}
}