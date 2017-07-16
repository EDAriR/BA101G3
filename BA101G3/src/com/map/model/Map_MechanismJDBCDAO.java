package com.map.model;

import java.util.*;
import java.sql.*;

public class Map_MechanismJDBCDAO implements Map_MechanismDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO map_mechanism (map_no, mem_no, mapc_no, map_name, map_adds, map_addc, map_long, map_lat, map_phone, map_mail, map_cnt, map_photo, map_photo1, map_photo2, map_photo3, map_photo4, map_photo5) VALUES ('MAP'||LPAD(to_char(map_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM map_mechanism";
	private static final String GET_ONE_STMT = "SELECT * FROM map_mechanism WHERE map_no = ?";
	// 刪除資料
	private static final String DELETE_MAP_COMMENTs = "DELETE FROM map_comment WHERE map_no = ?";
	private static final String DELETE_MAP = "DELETE FROM map_mechanism WHERE map_no = ?";
	// 修改資料
	private static final String UPDATE = "UPDATE map_mechanism set mem_no=?, mapc_no=?, map_name=?, map_adds=?, map_addc=?, map_long=?, map_lat=?, map_phone=?, map_mail=?, map_cnt=?, map_photo=?, map_photo1=?, map_photo2=?, map_photo3=?, map_photo4=?, map_photo5=? WHERE map_no=?";

	@Override
	public void insert(Map_MechanismVO map_mechanismVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "map_no" }; // 有使用sequence產生編號的話才要寫
			pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數

			pstmt.setString(1, map_mechanismVO.getMem_no());
			pstmt.setString(2, map_mechanismVO.getMapc_no());
			pstmt.setString(3, map_mechanismVO.getMap_name());
			pstmt.setString(4, map_mechanismVO.getMap_adds());
			pstmt.setString(5, map_mechanismVO.getMap_addc());
			pstmt.setDouble(6, map_mechanismVO.getMap_long());
			pstmt.setDouble(7, map_mechanismVO.getMap_lat());
			pstmt.setString(8, map_mechanismVO.getMap_phone());
			pstmt.setString(9, map_mechanismVO.getMap_mail());
			pstmt.setString(10, map_mechanismVO.getMap_cnt());
			pstmt.setBytes(11, map_mechanismVO.getMap_photo());
			pstmt.setBytes(12, map_mechanismVO.getMap_photo1());
			pstmt.setBytes(13, map_mechanismVO.getMap_photo2());
			pstmt.setBytes(14, map_mechanismVO.getMap_photo3());
			pstmt.setBytes(15, map_mechanismVO.getMap_photo4());
			pstmt.setBytes(16, map_mechanismVO.getMap_photo5());

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
	public void update(Map_MechanismVO map_mechanismVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, map_mechanismVO.getMem_no());
			pstmt.setString(2, map_mechanismVO.getMapc_no());
			pstmt.setString(3, map_mechanismVO.getMap_name());
			pstmt.setString(4, map_mechanismVO.getMap_adds());
			pstmt.setString(5, map_mechanismVO.getMap_addc());
			pstmt.setDouble(6, map_mechanismVO.getMap_long());
			pstmt.setDouble(7, map_mechanismVO.getMap_lat());
			pstmt.setString(8, map_mechanismVO.getMap_phone());
			pstmt.setString(9, map_mechanismVO.getMap_mail());
			pstmt.setString(10, map_mechanismVO.getMap_cnt());
			pstmt.setBytes(11, map_mechanismVO.getMap_photo());
			pstmt.setBytes(12, map_mechanismVO.getMap_photo1());
			pstmt.setBytes(13, map_mechanismVO.getMap_photo2());
			pstmt.setBytes(14, map_mechanismVO.getMap_photo3());
			pstmt.setBytes(15, map_mechanismVO.getMap_photo4());
			pstmt.setBytes(16, map_mechanismVO.getMap_photo5());
			pstmt.setString(17, map_mechanismVO.getMap_no());

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
	public void delete(String map_no) {
		int updateCount_MAP_COMMENTs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			// 先刪除地圖評論(多)
			pstmt = con.prepareStatement(DELETE_MAP_COMMENTs);
			pstmt.setString(1, map_no);
			updateCount_MAP_COMMENTs = pstmt.executeUpdate();

			// 再刪除地圖機構(一)
			pstmt = con.prepareStatement(DELETE_MAP);
			pstmt.setString(1, map_no);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除地圖機構" + map_no + "時,共有地圖機構評論" + updateCount_MAP_COMMENTs + "個同時被刪除");

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
	public Map_MechanismVO findByPrimaryKey(String map_no) {

		Map_MechanismVO map_mechanismVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, map_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				map_mechanismVO = new Map_MechanismVO();
				map_mechanismVO.setMap_no(rs.getString("map_no"));
				map_mechanismVO.setMem_no(rs.getString("mem_no"));
				map_mechanismVO.setMapc_no(rs.getString("mapc_no"));
				map_mechanismVO.setMap_name(rs.getString("map_name"));
				map_mechanismVO.setMap_adds(rs.getString("map_adds"));
				map_mechanismVO.setMap_addc(rs.getString("map_addc"));
				map_mechanismVO.setMap_long(rs.getDouble("map_long"));
				map_mechanismVO.setMap_lat(rs.getDouble("map_lat"));
				map_mechanismVO.setMap_phone(rs.getString("map_phone"));
				map_mechanismVO.setMap_mail(rs.getString("map_mail"));
				map_mechanismVO.setMap_cnt(rs.getString("map_cnt"));
				map_mechanismVO.setMap_photo(rs.getBytes("map_photo"));
				map_mechanismVO.setMap_photo1(rs.getBytes("map_photo1"));
				map_mechanismVO.setMap_photo2(rs.getBytes("map_photo2"));
				map_mechanismVO.setMap_photo3(rs.getBytes("map_photo3"));
				map_mechanismVO.setMap_photo4(rs.getBytes("map_photo4"));
				map_mechanismVO.setMap_photo5(rs.getBytes("map_photo5"));
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
		return map_mechanismVO;
	}

	@Override
	public List<Map_MechanismVO> getAll() {
		List<Map_MechanismVO> list = new ArrayList<Map_MechanismVO>();
		Map_MechanismVO map_mechanismVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				map_mechanismVO = new Map_MechanismVO();
				map_mechanismVO.setMap_no(rs.getString("map_no"));
				map_mechanismVO.setMem_no(rs.getString("mem_no"));
				map_mechanismVO.setMapc_no(rs.getString("mapc_no"));
				map_mechanismVO.setMap_name(rs.getString("map_name"));
				map_mechanismVO.setMap_adds(rs.getString("map_adds"));
				map_mechanismVO.setMap_addc(rs.getString("map_addc"));
				map_mechanismVO.setMap_long(rs.getDouble("map_long"));
				map_mechanismVO.setMap_lat(rs.getDouble("map_lat"));
				map_mechanismVO.setMap_phone(rs.getString("map_phone"));
				map_mechanismVO.setMap_mail(rs.getString("map_mail"));
				map_mechanismVO.setMap_cnt(rs.getString("map_cnt"));
				map_mechanismVO.setMap_photo(rs.getBytes("map_photo"));
				map_mechanismVO.setMap_photo1(rs.getBytes("map_photo1"));
				map_mechanismVO.setMap_photo2(rs.getBytes("map_photo2"));
				map_mechanismVO.setMap_photo3(rs.getBytes("map_photo3"));
				map_mechanismVO.setMap_photo4(rs.getBytes("map_photo4"));
				map_mechanismVO.setMap_photo5(rs.getBytes("map_photo5"));
				list.add(map_mechanismVO); // Store the row in the list
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		Map_MechanismJDBCDAO dao = new Map_MechanismJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增 OK
//		 Map_MechanismVO map_mechanismVO1 = new Map_MechanismVO();
//		 map_mechanismVO1.setMap_name("金知園");
//		 map_mechanismVO1.setMapc_no("0");
//		 map_mechanismVO1.setMap_adds("台北市內湖區");
//		 map_mechanismVO1.setMap_addc("citylink");
//		 map_mechanismVO1.setMap_long(123.123456);
//		 map_mechanismVO1.setMap_lat(156.39482);
//		 map_mechanismVO1.setMap_phone("028376452");
//		 map_mechanismVO1.setMap_mail("456@jaidkn.com");
//		 map_mechanismVO1.setMap_cnt("還不錯的地方");
//		
//		 dao.insert(map_mechanismVO1);

		// 修改 OK
//		 Map_MechanismVO map_mechanismVO2 = new Map_MechanismVO();
//		 map_mechanismVO2.setMap_no("MAP00004");
//		 map_mechanismVO2.setMap_cnt("衛生有點不好");
//		 map_mechanismVO2.setMap_adds("台北市內湖區");
//		 map_mechanismVO2.setMap_addc("citylink");
//		 map_mechanismVO2.setMap_long(123.123456);
//		 map_mechanismVO2.setMap_lat(156.39482);
//		 map_mechanismVO2.setMap_phone("028376452");
//		 map_mechanismVO2.setMap_mail("456@jaidkn.com");
//		 map_mechanismVO2.setMap_name("金雞園");
//		
//		 dao.update(map_mechanismVO2);

		// 刪除 OK
//		 dao.delete("MAP00001");

		// 查詢 OK
//		 Map_MechanismVO map_mechanismVO3 = dao.findByPrimaryKey("MAP00003");
//		 System.out.print(map_mechanismVO3.getMap_no() + ",");
//		 System.out.print(map_mechanismVO3.getMapc_no()+ ",");
//		 System.out.print(map_mechanismVO3.getMap_name()+ ",");
//		 System.out.println(map_mechanismVO3.getMap_cnt());
//		 System.out.println("---------------------");

		// 查詢全部 OK
//		 List<Map_MechanismVO> list = dao.getAll();
//		 for (Map_MechanismVO map_mechanism : list) {
//		 System.out.print(map_mechanism.getMap_no() + ",");
//		 System.out.print(map_mechanism.getMapc_no()+ ",");
//		 System.out.print(map_mechanism.getMap_name()+ ",");
//		 System.out.print(map_mechanism.getMap_cnt());
//		 System.out.println();
//		 }

	}
}