package com.admin.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin_AuthorityDAO implements Admin_AuthorityDAO_interface {
    // 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG3");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

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
    public void insert(Admin_AuthorityVO admin_AuthorityVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();

            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, admin_AuthorityVO.getAdm_no());
            pstmt.setString(2, admin_AuthorityVO.getAuth_no());
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
    public void delete(String adm_no, String auth_no) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();

            pstmt = con.prepareStatement(DELETE_ADMIN_AUTHORITY);
            pstmt.setString(1, adm_no);
            pstmt.setString(2, auth_no);
            pstmt.executeUpdate();

            System.out.println("Delete Admin Authority:" + adm_no + " and " + auth_no);

            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
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
    public List<Admin_AuthorityVO> findByAdmNo(String adm_no) {

        List<Admin_AuthorityVO> list = new ArrayList<Admin_AuthorityVO>();
        Admin_AuthorityVO admin_authorityVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_BY_ADM_NO_STMT);
            pstmt.setString(1, adm_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                admin_authorityVO = new Admin_AuthorityVO();
                admin_authorityVO.setAdm_no(rs.getString("adm_no"));
                admin_authorityVO.setAuth_no(rs.getString("auth_no"));
                list.add(admin_authorityVO); // Store the row in the list
            }
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

    @Override
    public List<Admin_AuthorityVO> findByAuthNo(String auth_no) {

        

        List<Admin_AuthorityVO> list = new ArrayList<Admin_AuthorityVO>();
        Admin_AuthorityVO admin_authorityVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
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

    @Override
    public Admin_AuthorityVO findByPrimaryKey(String adm_no, String auth_no) {

        Admin_AuthorityVO admin_authorityVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
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
        return admin_authorityVO;
    }

    @Override
    public List<Admin_AuthorityVO> getAll() {

        List<Admin_AuthorityVO> list = new ArrayList<Admin_AuthorityVO>();
        Admin_AuthorityVO admin_AuthorityVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                admin_AuthorityVO = new Admin_AuthorityVO();
                admin_AuthorityVO.setAdm_no(rs.getString("adm_no"));
                admin_AuthorityVO.setAuth_no(rs.getString("auth_no"));
                list.add(admin_AuthorityVO); // Store the row in the list
            }
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
}