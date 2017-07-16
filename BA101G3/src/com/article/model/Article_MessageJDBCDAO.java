package com.article.model;

import java.util.*;
import java.sql.*;


public class Article_MessageJDBCDAO implements Article_MessageDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO article_message (amsg_no, art_no, mem_no, amsg_date, amsg_cnt) VALUES ('AMSG'||LPAD(to_char(amsg_no_seq.NEXTVAL),4,'0'), ?, ?, SYSDATE, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM article_message";
	private static final String GET_ONE_STMT = "SELECT * FROM article_message WHERE amsg_no = ?";
	// 刪除資料
	private static final String DELETE_ARTICLE_MESSAGE_REPORTs = "DELETE FROM article_message_report WHERE amsg_no=?";
	private static final String DELETE_ARTICLE_MESSAGE = "DELETE FROM article_message WHERE amsg_no = ?";	

	@Override
	public void insert(Article_MessageVO article_MessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "amsg_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, article_MessageVO.getArt_no());
			pstmt.setString(2, article_MessageVO.getMem_no());
			pstmt.setString(3, article_MessageVO.getAmsg_cnt());
		
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
	public void delete(String amsg_no) {
		int updateCount_ARTICLE_MESSAGE_REPORTs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除文章留言檢舉(多)
			pstmt = con.prepareStatement(DELETE_ARTICLE_MESSAGE_REPORTs);
			pstmt.setString(1, amsg_no);
			updateCount_ARTICLE_MESSAGE_REPORTs = pstmt.executeUpdate();
			// 再刪除文章留言(一)
			pstmt = con.prepareStatement(DELETE_ARTICLE_MESSAGE);
			pstmt.setString(1, amsg_no);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除文章留言編號" + amsg_no + "時,共有文章留言檢舉" + updateCount_ARTICLE_MESSAGE_REPORTs
					+ "個同時被刪除");
			
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
	public Article_MessageVO findByPrimaryKey(String amsg_no) {

		Article_MessageVO article_MessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, amsg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_MessageVO = new Article_MessageVO();
				article_MessageVO.setAmsg_no(rs.getString("amsg_no"));
				article_MessageVO.setArt_no(rs.getString("art_no"));
				article_MessageVO.setMem_no(rs.getString("mem_no"));
				article_MessageVO.setAmsg_date(rs.getTimestamp("amsg_date"));
				article_MessageVO.setAmsg_cnt(rs.getString("amsg_cnt"));
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
		return article_MessageVO;
	}

	@Override
	public List<Article_MessageVO> getAll() {
		List<Article_MessageVO> list = new ArrayList<Article_MessageVO>();
		Article_MessageVO article_MessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_MessageVO = new Article_MessageVO();
				article_MessageVO.setAmsg_no(rs.getString("amsg_no"));
				article_MessageVO.setArt_no(rs.getString("art_no"));
				article_MessageVO.setMem_no(rs.getString("mem_no"));
				article_MessageVO.setAmsg_date(rs.getTimestamp("amsg_date"));
				article_MessageVO.setAmsg_cnt(rs.getString("amsg_cnt"));
				list.add(article_MessageVO); // Store the row in the list
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

		Article_MessageJDBCDAO dao = new Article_MessageJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Article_MessageVO article_messageVO1 = new Article_MessageVO();
//		article_messageVO1.setArt_no("ART00001");
//		article_messageVO1.setMem_no("M0000003");
//		article_messageVO1.setAmsg_cnt("測試文章留言內容");
//		dao.insert(article_messageVO1);
//		System.out.println("新增文章留言");

		// 刪除 OK
//		dao.delete("AMSG0004");
//		System.out.println("刪除完成");

		// 查詢 OK
//		Article_MessageVO article_messageVO3 = dao.findByPrimaryKey("AMSG0004");
//		System.out.print(article_messageVO3.getAmsg_no() + ",");
//		System.out.print(article_messageVO3.getArt_no() + ",");
//		System.out.print(article_messageVO3.getMem_no() + ",");
//		System.out.print(article_messageVO3.getAmsg_date() + ",");
//		System.out.println(article_messageVO3.getAmsg_cnt());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<Article_MessageVO> list = dao.getAll();
//		for (Article_MessageVO article_messageVO : list) {
//			System.out.print(article_messageVO.getAmsg_no() + ",");
//			System.out.print(article_messageVO.getArt_no() + ",");
//			System.out.print(article_messageVO.getMem_no() + ",");
//			System.out.print(article_messageVO.getAmsg_date() + ",");
//			System.out.print(article_messageVO.getAmsg_cnt()	);
//			System.out.println();
//		}
		
	}
}