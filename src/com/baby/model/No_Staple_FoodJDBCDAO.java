package com.baby.model;

import java.util.*;

import java.sql.*;


public class No_Staple_FoodJDBCDAO implements No_Staple_FoodDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// �s�W���
	private static final String INSERT_STMT = "INSERT INTO no_staple_food (nf_no, gint_no, nf_title, nf_cnt) VALUES ('NF'||LPAD(to_char(nf_no_seq.NEXTVAL), 6, '0'), ?, ?, ?)";
	// �d�߸��
	private static final String GET_ALL_STMT = "SELECT * FROM no_staple_food";
	private static final String GET_ONE_STMT = "SELECT * FROM no_staple_food WHERE nf_no = ?";
	// �ק���
	private static final String UPDATE_STMT = "UPDATE no_staple_food set gint_no=?, nf_title=?, nf_cnt=? WHERE nf_no=?";
	// �R�����
	private static final String DELETE_NO_STAPLE_FOOD = "DELETE FROM no_staple_food WHERE nf_no = ?";

	
	@Override
	public void insert(No_Staple_FoodVO no_staple_foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, no_staple_foodVO.getGint_no());
			pstmt.setString(2, no_staple_foodVO.getNf_title());
			pstmt.setString(3, no_staple_foodVO.getNf_cnt());

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
	public void update(No_Staple_FoodVO no_staple_foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, no_staple_foodVO.getGint_no());
			pstmt.setString(2, no_staple_foodVO.getNf_title());
			pstmt.setString(3, no_staple_foodVO.getNf_cnt());
			pstmt.setString(4, no_staple_foodVO.getNf_no());

			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 1���]�w�� pstm.executeUpdate()���e
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_NO_STAPLE_FOOD);
			pstmt.setString(1, nf_no);
			pstmt.executeUpdate();

			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
			System.out.println("�R���ƭ��~" + nf_no);

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

		No_Staple_FoodJDBCDAO dao = new No_Staple_FoodJDBCDAO();
		// ���լݬݨC�ӫ��O�O�_�i�H�ϥ�
		// �s�W OK
//		No_Staple_FoodVO no_staple_foodVO1 = new No_Staple_FoodVO();
//		no_staple_foodVO1.setGint_no("17");
//		no_staple_foodVO1.setNf_title("���շs�W");
//		no_staple_foodVO1.setNf_cnt("���շs�W");
//		dao.insert(no_staple_foodVO1);
		
		// �ק� OK
//		No_Staple_FoodVO no_staple_foodVO2 = new No_Staple_FoodVO();
//		no_staple_foodVO2.setNf_no("NF000023");
//		no_staple_foodVO2.setGint_no("17");
//		no_staple_foodVO2.setNf_title("���խק�");
//		no_staple_foodVO2.setNf_cnt("���խק�");
//		dao.update(no_staple_foodVO2);

		// �R�� OK
//		dao.delete("NF000023");
		
		// �d�� OK
//		No_Staple_FoodVO no_staple_foodVO3 = dao.findByPrimaryKey("NF000022");
//		System.out.print(no_staple_foodVO3.getNf_no() + ",");
//		System.out.print(no_staple_foodVO3.getGint_no() + ",");
//		System.out.print(no_staple_foodVO3.getNf_title() + ",");
//		System.out.println(no_staple_foodVO3.getNf_cnt());
//		System.out.println("---------------------");

		// �d�ߥ��� OK
//		List<No_Staple_FoodVO> list = dao.getAll();
//		for (No_Staple_FoodVO no_staple_foodVO : list) {
//			System.out.print(no_staple_foodVO.getNf_no() + ",");
//			System.out.print(no_staple_foodVO.getGint_no() + ",");
//			System.out.print(no_staple_foodVO.getNf_title() + ",");
//			System.out.print(no_staple_foodVO.getNf_cnt());
//			System.out.println();
//		}
		
	}
}