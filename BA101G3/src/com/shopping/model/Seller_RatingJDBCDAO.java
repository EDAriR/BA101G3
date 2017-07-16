package com.shopping.model;

import java.util.*;
import java.sql.*;


public class Seller_RatingJDBCDAO implements Seller_RatingDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 穝糤戈
	private static final String INSERT_STMT = "INSERT INTO seller_rating (pro_no, mem_no, sell_rating) VALUES (?, ?, ?)";
	// 琩高戈
	private static final String GET_ALL_STMT = "SELECT * FROM seller_rating";
	private static final String GET_ONE_STMT = "SELECT * FROM seller_rating where pro_no = ? and mem_no = ?";
	private static final String GET_BY_PRO_NO_STMT = "SELECT * FROM seller_rating WHERE pro_no =?";

	@Override
	public void insert(Seller_RatingVO seller_ratingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, seller_ratingVO.getPro_no());
			pstmt.setString(2, seller_ratingVO.getMem_no());
			pstmt.setInt(3, seller_ratingVO.getSell_rating());

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
	public Seller_RatingVO findByPrimaryKey(String pro_no, String mem_no) {

		Seller_RatingVO seller_ratingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pro_no);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				seller_ratingVO = new Seller_RatingVO();
				seller_ratingVO.setPro_no(rs.getString("pro_no"));
				seller_ratingVO.setMem_no(rs.getString("mem_no"));
				seller_ratingVO.setSell_rating(rs.getInt("sell_rating"));

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
		return seller_ratingVO;
	}

	@Override
	public List<Seller_RatingVO> findByProNo(String pro_no) {
		List<Seller_RatingVO> list = new ArrayList<Seller_RatingVO>();
		Seller_RatingVO seller_ratingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_PRO_NO_STMT);

			pstmt.setString(1, pro_no);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				seller_ratingVO = new Seller_RatingVO();
				seller_ratingVO.setPro_no(rs.getString("pro_no"));
				seller_ratingVO.setMem_no(rs.getString("mem_no"));
				seller_ratingVO.setSell_rating(rs.getInt("sell_rating"));
				list.add(seller_ratingVO); // Store the row in the list
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
	public List<Seller_RatingVO> getAll() {
		List<Seller_RatingVO> list = new ArrayList<Seller_RatingVO>();
		Seller_RatingVO seller_ratingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				seller_ratingVO = new Seller_RatingVO();
				seller_ratingVO.setPro_no(rs.getString("pro_no"));
				seller_ratingVO.setMem_no(rs.getString("mem_no"));
				seller_ratingVO.setSell_rating(rs.getInt("sell_rating"));
				list.add(seller_ratingVO); // Store the row in the list
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

		Seller_RatingJDBCDAO dao = new Seller_RatingJDBCDAO();
		// 代刚–琌ㄏノ
		// 穝糤 OK
//		Seller_RatingVO seller_ratingVO1 = new Seller_RatingVO();
//		seller_ratingVO1.setPro_no("PRO00009");
//		seller_ratingVO1.setMem_no("M0000003");
//		seller_ratingVO1.setSell_rating(3);
//		dao.insert(seller_ratingVO1);

		// 琩高 OK
//		Seller_RatingVO seller_ratingVO3 = dao.findByPrimaryKey("PRO00007", "M0000002");
//		System.out.print(seller_ratingVO3.getPro_no() + ",");
//		System.out.print(seller_ratingVO3.getMem_no() + ",");
//		System.out.println(seller_ratingVO3.getSell_rating());
//		System.out.println("---------------------");
		
		// 琩高pro_no OK
//		List<Seller_RatingVO> list = dao.findByProNo("PRO00007");
//		for (Seller_RatingVO seller_rating : list) {
//			System.out.print(seller_rating.getPro_no() + ",");
//			System.out.print(seller_rating.getMem_no()+ ",");
//			System.out.print(seller_rating.getSell_rating());
//			System.out.println();
//		}

		// 琩高场 OK
//		List<Seller_RatingVO> list = dao.getAll();
//		for (Seller_RatingVO seller_rating : list) {
//			System.out.print(seller_rating.getPro_no() + ",");
//			System.out.print(seller_rating.getMem_no()+ ",");
//			System.out.print(seller_rating.getSell_rating());
//			System.out.println();
//		}
		
	}
}