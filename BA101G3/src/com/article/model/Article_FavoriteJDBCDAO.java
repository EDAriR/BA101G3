package com.article.model;

import java.util.*;
import java.sql.*;


public class Article_FavoriteJDBCDAO implements Article_FavoriteDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO article_favorite (mem_no, art_no) VALUES (?, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT mem_no, art_no FROM article_favorite";
	private static final String GET_ONE_STMT = "SELECT mem_no, art_no FROM article_favorite WHERE mem_no = ? and art_no =?";
	private static final String GET_BY_MEM_NO_STMT = "SELECT mem_no, art_no FROM article_favorite WHERE mem_no =?";
	private static final String GET_BY_ART_NO_STMT = "SELECT mem_no, art_no FROM article_favorite WHERE art_no =?";
	// 刪除資料
	private static final String DELETE_ARTICLE_FAVORITE = "DELETE FROM article_favorite WHERE mem_no = ? and art_no =?";	

	@Override
	public void insert(Article_FavoriteVO article_favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT); 
			pstmt.setString(1, article_favoriteVO.getMem_no());
			pstmt.setString(2, article_favoriteVO.getArt_no());

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
	public void delete(String mem_no, String art_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_ARTICLE_FAVORITE);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, art_no);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除會員編號" + mem_no + "關注的文章編號" + art_no);
			
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
	public Article_FavoriteVO findByPrimaryKey(String mem_no , String art_no) {

		Article_FavoriteVO article_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setMem_no(rs.getString("mem_no"));
				article_favoriteVO.setArt_no(rs.getString("art_no"));
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
		return article_favoriteVO;
	}

	@Override
	public List<Article_FavoriteVO> getAll() {
		List<Article_FavoriteVO> list = new ArrayList<Article_FavoriteVO>();
		Article_FavoriteVO article_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setMem_no(rs.getString("mem_no"));
				article_favoriteVO.setArt_no(rs.getString("art_no"));
				list.add(article_favoriteVO); // Store the row in the list
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


	@Override
	public List<Article_FavoriteVO> findByMemNo(String mem_no) {
		List<Article_FavoriteVO> list = new ArrayList<Article_FavoriteVO>();
		Article_FavoriteVO article_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MEM_NO_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setMem_no(rs.getString("mem_no"));
				article_favoriteVO.setArt_no(rs.getString("art_no"));
				list.add(article_favoriteVO);
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
		return list;
	}

	@Override
	public List<Article_FavoriteVO> findByArtNo(String art_no) {
		List<Article_FavoriteVO> list = new ArrayList<Article_FavoriteVO>();
		Article_FavoriteVO article_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_ART_NO_STMT);

			pstmt.setString(1, art_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setMem_no(rs.getString("mem_no"));
				article_favoriteVO.setArt_no(rs.getString("art_no"));
				list.add(article_favoriteVO);
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
		return list;
	}

	public static void main(String[] args) {
	
			Article_FavoriteJDBCDAO dao = new Article_FavoriteJDBCDAO();
			// 測試看看每個指令是否可以使用
			// 新增 OK
//			Article_FavoriteVO article_favoriteVO1 = new Article_FavoriteVO();
//			article_favoriteVO1.setMem_no("M0000006");
//			article_favoriteVO1.setArt_no("ART00004");
//			dao.insert(article_favoriteVO1);
//			System.out.println("新增一筆收藏文章");

			// 刪除 OK
//			dao.delete("M0000006", "ART00001");
//			System.out.println("刪除成功");
	
			// 查詢 OK
//			Article_FavoriteVO article_favoriteVO3 = dao.findByPrimaryKey("M0000006","ART00001");
//			System.out.print(article_favoriteVO3.getMem_no() + ",");
//			System.out.println(article_favoriteVO3.getArt_no());
//			System.out.println("---------------------");
	
			// 查詢全部 OK
//			List<Article_FavoriteVO> list = dao.getAll();
//			for (Article_FavoriteVO article_favoriteVO : list) {
//				System.out.print(article_favoriteVO.getMem_no() + ",");
//				System.out.print(article_favoriteVO.getArt_no());
//				System.out.println();
//			}
			
			//查單一會員的收藏文章 OK
//			List<Article_FavoriteVO> list2 = dao.findByMemNo("M0000006");
//			for(Article_FavoriteVO art_fav2 : list2){
//			System.out.print("會員編號:"+art_fav2.getMem_no() + ",");
//			System.out.println("收藏了文章"+art_fav2.getArt_no() );
//			}
//			System.out.println("---------以上是單一會員的收藏文章------------");
			
			//查單一文章備哪些會員收藏 OK
//			List<Article_FavoriteVO> list3 = dao.findByArtNo("ART00001");
//			for(Article_FavoriteVO art_fav3 : list3){
//			System.out.print("文章編號:"+art_fav3.getArt_no() + ",");
//			System.out.println("被會員"+art_fav3.getMem_no()+"收藏");
//			}
//			System.out.println("---------以上是單一文章被哪些會員收藏------------");			
		}
}