package com.map.model;

import java.util.*;
import java.sql.*;


public class Map_CommentJDBCDAO implements Map_CommentDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO map_comment (mcmt_no, mem_no, map_no, mcmt_date, mcmt_cnt) VALUES ('MCMT'||LPAD(to_char(mcmt_no_seq.NEXTVAL), 4, '0'), ?, ?, SYSDATE, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM map_comment";
	private static final String GET_ONE_STMT = "SELECT * FROM map_comment where mcmt_no = ?";
	// 刪除資料
	private static final String DELETE_MCMT = "DELETE FROM map_comment where mcmt_no = ?";	
	@Override
	public void insert(Map_CommentVO map_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "map_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt.setString(1, map_commentVO.getMem_no());
			pstmt.setString(2, map_commentVO.getMap_no());
			pstmt.setString(3, map_commentVO.getMcmt_cnt());
	

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
	public void delete(String mcmt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_MCMT);
			pstmt.setString(1, mcmt_no);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除地圖機構評論： " + mcmt_no);
			
			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Map_CommentVO findByPrimaryKey(String mcmt_no) {

		Map_CommentVO map_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mcmt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				map_commentVO = new Map_CommentVO();
				map_commentVO.setMcmt_no(rs.getString("mcmt_no"));
				map_commentVO.setMem_no(rs.getString("mem_no"));
				map_commentVO.setMap_no(rs.getString("map_no"));
				map_commentVO.setMcmt_date(rs.getTimestamp("mcmt_date"));
				map_commentVO.setMcmt_cnt(rs.getString("mcmt_cnt"));
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
		return map_commentVO;
	}

	@Override
	public List<Map_CommentVO> getAll() {
		List<Map_CommentVO> list = new ArrayList<Map_CommentVO>();
		Map_CommentVO map_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				map_commentVO = new Map_CommentVO();
				map_commentVO.setMcmt_no(rs.getString("mcmt_no"));
				map_commentVO.setMem_no(rs.getString("mem_no"));
				map_commentVO.setMap_no(rs.getString("map_no"));
				map_commentVO.setMcmt_date(rs.getTimestamp("mcmt_date"));
				map_commentVO.setMcmt_cnt(rs.getString("mcmt_cnt"));
				list.add(map_commentVO); // Store the row in the list
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

		Map_CommentJDBCDAO dao = new Map_CommentJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Map_CommentVO map_commentVO1 = new Map_CommentVO();
//		map_commentVO1.setMem_no("M0000004");
//		map_commentVO1.setMap_no("MAP00004");
//		map_commentVO1.setMcmt_date(java.sql.Timestamp.valueOf("2016-01-01 07:22:13"));
//		map_commentVO1.setMcmt_cnt("餐點精緻，還不錯，偏貴");
//		dao.insert(map_commentVO1);

		// 刪除 OK
//		dao.delete("MCMT0001");

		// 查詢 OK
//		Map_CommentVO map_commentVO3 = dao.findByPrimaryKey("MCMT0001");
//		System.out.print(map_commentVO3.getMcmt_no() + ",");
//		System.out.print(map_commentVO3.getMem_no() + ",");
//		System.out.print(map_commentVO3.getMap_no() + ",");
//		System.out.print(map_commentVO3.getMcmt_date() + ",");
//		System.out.println(map_commentVO3.getMcmt_cnt());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<Map_CommentVO> list = dao.getAll();
//		for (Map_CommentVO map_comment : list) {
//			System.out.print(map_comment.getMcmt_no() + ",");
//			System.out.print(map_comment.getMem_no() + ",");
//			System.out.print(map_comment.getMap_no() + ",");
//			System.out.print(map_comment.getMcmt_date() + ",");
//			System.out.print(map_comment.getMcmt_cnt());
//			System.out.println();
//		}
		
	}
}