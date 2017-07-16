package com.admin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Admin_AuthorityJDBCDAO implements Admin_AuthorityDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "ba101g3";
	private static final String PASSWORD = "baby";
	// 新增資料
    private static final String INSERT_STMT = "INSERT INTO admin_authority (adm_no, auth_no) VALUES (?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT * FROM admin_authority";
    private static final String GET_BY_ADM_NO_STMT = "SELECT * FROM admin_authority WHERE adm_no = ?";
    private static final String GET_BY_AUTH_NO = "SELECT * FROM admin_authority WHERE auth_no = ?";
    private static final String GET_BY_PK = "SELECT * FROM admin_authority WHERE adm_no = ? AND auth_no = ?";
    // 刪除資料
    private static final String DELETE_ADMIN_AUTHORITY = "DELETE FROM admin_authority WHERE adm_no = ? AND auth_no = ?";


    @Override
    public void insert(Admin_AuthorityVO admin_authorityVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, admin_authorityVO.getAdm_no());
            pstmt.setString(2, admin_authorityVO.getAuth_no());
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
    public void delete(String adm_no,String auth_no){

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            pstmt = con.prepareStatement(DELETE_ADMIN_AUTHORITY);
            pstmt.setString(1, adm_no);
            pstmt.setString(2, auth_no);
            pstmt.executeUpdate();

            System.out.println("Delete Admin Authority:" + adm_no + " and "+ auth_no);

            // Handle any DRIVER errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database DRIVER. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
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
	public List<Admin_AuthorityVO> findByAdmNo(String adm_no) {

        List<Admin_AuthorityVO> list = new ArrayList<Admin_AuthorityVO>();
		Admin_AuthorityVO admin_authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_ADM_NO_STMT);

			pstmt.setString(1, adm_no);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
	               admin_authorityVO = new Admin_AuthorityVO();
	               admin_authorityVO.setAdm_no(rs.getString("adm_no"));
	               admin_authorityVO.setAuth_no(rs.getString("auth_no"));
	               list.add(admin_authorityVO); // Store the row in the list
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
    public List<Admin_AuthorityVO> findByAuthNo(String auth_no) {

        

        List<Admin_AuthorityVO> list = new ArrayList<Admin_AuthorityVO>();
        Admin_AuthorityVO admin_authorityVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

        	Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_AUTH_NO);
            pstmt.setString(1, auth_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                admin_authorityVO = new Admin_AuthorityVO();
                admin_authorityVO.setAdm_no(rs.getString("adm_no"));
                admin_authorityVO.setAuth_no(rs.getString("auth_no"));
                list.add(admin_authorityVO); // Store the row in the list
            }
            // Handle any SQL errors
        } catch (Exception se) {
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

    @Override
    public Admin_AuthorityVO findByPrimaryKey(String adm_no, String auth_no) {
    	
    	Admin_AuthorityVO admin_authorityVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

        	Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BY_PK);

            pstmt.setString(1, adm_no);
            pstmt.setString(2, auth_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                admin_authorityVO = new Admin_AuthorityVO();
                admin_authorityVO.setAdm_no(rs.getString("adm_no"));
                admin_authorityVO.setAuth_no(rs.getString("auth_no"));
            }
            // Handle any SQL errors
        } catch (Exception se) {
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
        return admin_authorityVO;
    }

	public List<Admin_AuthorityVO> getAll(){

        List<Admin_AuthorityVO> list = new ArrayList<Admin_AuthorityVO>();
        Admin_AuthorityVO admin_authorityVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                admin_authorityVO = new Admin_AuthorityVO();
                admin_authorityVO.setAdm_no(rs.getString("adm_no"));
                admin_authorityVO.setAuth_no(rs.getString("auth_no"));
                list.add(admin_authorityVO); // Store the row in the list
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

        Admin_AuthorityJDBCDAO dao = new Admin_AuthorityJDBCDAO();

        // 新增 OK
//        Admin_AuthorityVO admin_authorityVO1 = new Admin_AuthorityVO();
//        admin_authorityVO1.setAdm_no("ADM00002");
//        admin_authorityVO1.setAuth_no("AF08");
//        dao.insert(admin_authorityVO1);
//        System.out.println("新增!");

        // 刪除 OK
//		dao.delete("ADM00002","AF04");
//		System.out.println("delete");

        // 查詢 adm_no OK
//        List<Admin_AuthorityVO> list = dao.findByAdmNo("ADM00002");
//		for (Admin_AuthorityVO admin_authorityVO : list) {
//			System.out.print(admin_authorityVO.getAdm_no() + ",");
//			System.out.println(admin_authorityVO.getAuth_no());
//			System.out.println("---------------------");
//		}

        // 查詢全部 OK
//		List<Admin_AuthorityVO> list = dao.getAll();
//		for (Admin_AuthorityVO admin_authorityVO : list) {
//			System.out.print(admin_authorityVO.getAdm_no() + ",");
//			System.out.print(admin_authorityVO.getAuth_no());
//			System.out.println();
//		}

    }
	
}