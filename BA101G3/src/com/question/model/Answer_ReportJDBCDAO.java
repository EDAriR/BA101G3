package com.question.model;

import java.util.*;
import java.sql.*;


public class Answer_ReportJDBCDAO implements Answer_ReportDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
	private static final String INSERT_STMT = "INSERT INTO answer_report (ans_no, mem_no, ansrpt_date, ansrpt_rsn, ansrpt_is_cert, ansrpt_unrsn ) VALUES (?, ?, SYSDATE, ?, '0', ?)";
	// 查詢資料
	private static final String GET_ALL_STMT = "SELECT * FROM answer_report";
	private static final String GET_ONE_STMT = "SELECT * FROM answer_report WHERE ans_no = ? AND mem_no=? ";
	// 修改資料
	private static final String UPDATE = "UPDATE answer_report SET ansrpt_is_cert=?, ansrpt_unrsn=? WHERE ans_no=? AND mem_no=?";

	@Override
	public void insert(Answer_ReportVO answer_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT); 
			pstmt.setString(1, answer_reportVO.getAns_no());
			pstmt.setString(2, answer_reportVO.getMem_no());
			pstmt.setString(3, answer_reportVO.getAnsrpt_rsn());
			pstmt.setString(4, answer_reportVO.getAnsrpt_unrsn());
			
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
	public void update(Answer_ReportVO answer_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, answer_reportVO.getAnsrpt_is_cert());
			pstmt.setString(2, answer_reportVO.getAnsrpt_unrsn());
			pstmt.setString(3, answer_reportVO.getAns_no());
			pstmt.setString(4, answer_reportVO.getMem_no());
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
	public Answer_ReportVO findByPrimaryKey(String ans_no, String mem_no) {

		Answer_ReportVO answer_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ans_no);
			pstmt.setString(2, mem_no);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				answer_reportVO = new Answer_ReportVO();
				answer_reportVO.setAns_no(rs.getString("ans_no"));
				answer_reportVO.setMem_no(rs.getString("mem_no"));
				answer_reportVO.setAnsrpt_date(rs.getTimestamp("ansrpt_date"));
				answer_reportVO.setAnsrpt_rsn(rs.getString("ansrpt_rsn"));
				answer_reportVO.setAnsrpt_is_cert(rs.getString("ansrpt_is_cert"));
				answer_reportVO.setAnsrpt_unrsn(rs.getString("ansrpt_unrsn"));
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
		return answer_reportVO;
	}

	@Override
	public List<Answer_ReportVO> getAll() {
		List<Answer_ReportVO> list = new ArrayList<Answer_ReportVO>();
		Answer_ReportVO answer_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				answer_reportVO = new Answer_ReportVO();
				answer_reportVO.setAns_no(rs.getString("ans_no"));
				answer_reportVO.setMem_no(rs.getString("mem_no"));
				answer_reportVO.setAnsrpt_date(rs.getTimestamp("ansrpt_date"));
				answer_reportVO.setAnsrpt_rsn(rs.getString("ansrpt_rsn"));
				answer_reportVO.setAnsrpt_is_cert(rs.getString("ansrpt_is_cert"));
				answer_reportVO.setAnsrpt_unrsn(rs.getString("ansrpt_unrsn"));
				list.add(answer_reportVO); // Store the row in the list
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

		Answer_ReportJDBCDAO dao = new Answer_ReportJDBCDAO();
		// 測試看看每個指令是否可以使用
		// 新增  OK
//		Answer_ReportVO answer_reportVO1 = new Answer_ReportVO();
//		answer_reportVO1.setAns_no("ANS00005");
//		answer_reportVO1.setMem_no("M0000004");
//		answer_reportVO1.setAnsrpt_rsn("不雅文字");
//		dao.insert(answer_reportVO1);
//		System.out.println("新增回答檢舉成功");

		// 修改 OK
//		Answer_ReportVO answer_reportVO2 = new Answer_ReportVO();
//		answer_reportVO2.setAns_no("ANS00005");
//		answer_reportVO2.setMem_no("M0000004");
//		answer_reportVO2.setAnsrpt_is_cert("1");
//		answer_reportVO2.setAnsrpt_unrsn("審核通過");
//		dao.update(answer_reportVO2);
//		System.out.println("修改成功");

		// 查詢 OK
//		Answer_ReportVO answer_reportVO3 = dao.findByPrimaryKey("ANS00005","M0000004");
//		System.out.print(answer_reportVO3.getAns_no() + ",");
//		System.out.print(answer_reportVO3.getMem_no() + ",");
//		System.out.print(answer_reportVO3.getAnsrpt_date() + ",");
//		System.out.print(answer_reportVO3.getAnsrpt_rsn() + ",");
//		System.out.print(answer_reportVO3.getAnsrpt_is_cert() + ",");
//		System.out.println(answer_reportVO3.getAnsrpt_unrsn());
//		System.out.println("---------------------");

		// 查詢全部 OK
//		List<Answer_ReportVO> list = dao.getAll();
//		for (Answer_ReportVO answer_reportVO : list) {
//			System.out.print(answer_reportVO.getAns_no() + ",");
//			System.out.print(answer_reportVO.getMem_no() + ",");
//			System.out.print(answer_reportVO.getAnsrpt_date() + ",");
//			System.out.print(answer_reportVO.getAnsrpt_rsn() + ",");
//			System.out.print(answer_reportVO.getAnsrpt_is_cert() + ",");
//			System.out.print(answer_reportVO.getAnsrpt_unrsn());
//			System.out.println();
//		}
		

	}
}