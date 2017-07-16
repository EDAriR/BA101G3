package com.article.model;

import java.util.*;
import java.sql.*;


public class Expert_FavoriteJDBCDAO implements Expert_FavoriteDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO expert_favorite (mem_no_s, mem_no_e) VALUES (?, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM expert_favorite";
	private static final String GET_ONE_STMT = "SELECT * FROM expert_favorite WHERE mem_no_s = ? and mem_no_e =?";
	private static final String GET_BY_MEM_NO_S_STMT = "SELECT * FROM expert_favorite WHERE mem_no_s=?";
	private static final String GET_BY_MEM_NO_E_STMT = "SELECT * FROM expert_favorite WHERE mem_no_e=?";
	// 刪除資料
	private static final String DELETE_STMT = "DELETE FROM expert_favorite WHERE mem_no_s = ? and mem_no_e =?";	

	@Override
	public void insert(Expert_FavoriteVO expert_favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT); 
			pstmt.setString(1, expert_favoriteVO.getMem_no_s());
			pstmt.setString(2, expert_favoriteVO.getMem_no_e());

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
	public void delete(String mem_no_s, String mem_no_e) {
		int updateCount_PRODUCTs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, mem_no_s);
			pstmt.setString(2, mem_no_e);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除會員編號" + mem_no_s + "關注的作家編號" + mem_no_e);
			
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
	public Expert_FavoriteVO findByPrimaryKey(String mem_no_s , String mem_no_e) {

		Expert_FavoriteVO product_classificationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no_s);
			pstmt.setString(2, mem_no_e);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_classificationVO = new Expert_FavoriteVO();
				product_classificationVO.setMem_no_s(rs.getString("mem_no_s"));
				product_classificationVO.setMem_no_e(rs.getString("mem_no_e"));
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
		return product_classificationVO;
	}

	@Override
	public List<Expert_FavoriteVO> findByMemNoS(String mem_no_s) {
		List<Expert_FavoriteVO> list = new ArrayList<Expert_FavoriteVO>();
		Expert_FavoriteVO expert_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MEM_NO_S_STMT);
			pstmt.setString(1, mem_no_s);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expert_favoriteVO = new Expert_FavoriteVO();
				expert_favoriteVO.setMem_no_s(rs.getString("mem_no_s"));
				expert_favoriteVO.setMem_no_e(rs.getString("mem_no_e"));
				list.add(expert_favoriteVO); // Store the row in the list
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
	public List<Expert_FavoriteVO> findByMemNoE(String mem_no_e) {
		List<Expert_FavoriteVO> list = new ArrayList<Expert_FavoriteVO>();
		Expert_FavoriteVO expert_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MEM_NO_E_STMT);
			pstmt.setString(1, mem_no_e);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expert_favoriteVO = new Expert_FavoriteVO();
				expert_favoriteVO.setMem_no_s(rs.getString("mem_no_s"));
				expert_favoriteVO.setMem_no_e(rs.getString("mem_no_e"));
				list.add(expert_favoriteVO); // Store the row in the list
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
	public List<Expert_FavoriteVO> getAll() {
		List<Expert_FavoriteVO> list = new ArrayList<Expert_FavoriteVO>();
		Expert_FavoriteVO expert_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expert_favoriteVO = new Expert_FavoriteVO();
				expert_favoriteVO.setMem_no_s(rs.getString("mem_no_s"));
				expert_favoriteVO.setMem_no_e(rs.getString("mem_no_e"));
				list.add(expert_favoriteVO); // Store the row in the list
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

		Expert_FavoriteJDBCDAO dao = new Expert_FavoriteJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		Expert_FavoriteVO expert_favoriteVO1 = new Expert_FavoriteVO();
//		expert_favoriteVO1.setMem_no_s("M0000006");
//		expert_favoriteVO1.setMem_no_e("M0000003");
//		dao.insert(expert_favoriteVO1);
//		System.out.println("新增一筆完成");

		// 刪除 OK
//		dao.delete("M0000001", "M0000005");
//		System.out.println("刪除完成");

		// 查詢複合主鍵 OK
//		Expert_FavoriteVO expert_favoriteVO3 = dao.findByPrimaryKey("M0000001","M0000005");
//		System.out.print(expert_favoriteVO3.getMem_no_s() + ",");
//		System.out.println(expert_favoriteVO3.getMem_no_e());
//		System.out.println("---------------------");

		// 查詢 mem_no_s OK
//		List<Expert_FavoriteVO> list = dao.findByMemNoS("M0000001");
//		for (Expert_FavoriteVO expert_favoriteVO : list) {
//			System.out.print(expert_favoriteVO.getMem_no_s() + ",");
//			System.out.print(expert_favoriteVO.getMem_no_e());
//			System.out.println();
//		}
		
		// 查詢 mem_no_e OK
//		List<Expert_FavoriteVO> list = dao.findByMemNoE("M0000006");
//		for (Expert_FavoriteVO expert_favoriteVO : list) {
//			System.out.print(expert_favoriteVO.getMem_no_s() + ",");
//			System.out.print(expert_favoriteVO.getMem_no_e());
//			System.out.println();
//		}
		
		// 查詢全部 OK
//		List<Expert_FavoriteVO> list = dao.getAll();
//		for (Expert_FavoriteVO expert_favoriteVO : list) {
//			System.out.print(expert_favoriteVO.getMem_no_s() + ",");
//			System.out.print(expert_favoriteVO.getMem_no_e());
//			System.out.println();
//		}
		
	}
}