package com.admin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdminJDBCDAO implements AdminDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "ba101g3";
    private static final String PASSWORD = "baby";
    // 新增資料
    private static final String INSERT_STMT = "INSERT INTO admin (adm_no, adm_acct, adm_pwd, adm_name, adm_mail) " +
            "VALUES ('ADM'||LPAD(to_char(adm_no_seq.nextval),5,'0'), ?, ?, ?, ?)";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT * FROM admin";
    private static final String GET_ONE_STMT = "SELECT * FROM admin WHERE adm_no = ?";
    // 修改資料
    private static final String UPDATE = "UPDATE admin SET adm_acct=?, adm_pwd=?, adm_name=?, adm_mail=? WHERE adm_no = ?";


    @Override
    public void insert(AdminVO adminVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String[] seq = {"adm_no"}; // 有使用sequence產生編號的話才要寫
            pstmt = con.prepareStatement(INSERT_STMT, seq); // 有使用sequence產生編號的話才要寫第二個參數

            pstmt.setString(1, adminVO.getAdm_acct());
            pstmt.setString(2, adminVO.getAdm_pwd());
            pstmt.setString(3, adminVO.getAdm_name());
            pstmt.setString(4, adminVO.getAdm_mail());

            pstmt.executeUpdate();

        } catch (Exception se) {
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
    public void update(AdminVO adminVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, adminVO.getAdm_acct());
            pstmt.setString(2, adminVO.getAdm_pwd());
            pstmt.setString(3, adminVO.getAdm_name());
            pstmt.setString(4, adminVO.getAdm_mail());
            pstmt.setString(5, adminVO.getAdm_no());

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
    public AdminVO findByPrimaryKey(String adm_no) {

        AdminVO adminVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, adm_no);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                adminVO = new AdminVO();
                adminVO.setAdm_no(rs.getString("adm_no"));
                adminVO.setAdm_acct(rs.getString("adm_acct"));
                adminVO.setAdm_pwd(rs.getString("adm_pwd"));
                adminVO.setAdm_name(rs.getString("adm_name"));
                adminVO.setAdm_mail(rs.getString("adm_mail"));
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
        return adminVO;
    }

    @Override
    public List<AdminVO> getAll() {

        List<AdminVO> list = new ArrayList<AdminVO>();
        AdminVO adminVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                adminVO = new AdminVO();
                adminVO.setAdm_no(rs.getString("adm_no"));
                adminVO.setAdm_acct(rs.getString("adm_acct"));
                adminVO.setAdm_pwd(rs.getString("adm_pwd"));
                adminVO.setAdm_name(rs.getString("adm_name"));
                adminVO.setAdm_mail(rs.getString("adm_mail"));
                list.add(adminVO); // Store the row in the list
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

        AdminJDBCDAO dao = new AdminJDBCDAO();

        // 新增 OK
//        AdminVO adminVO1 = new AdminVO();
//        adminVO1.setAdm_acct("adtestacct1");
//        adminVO1.setAdm_pwd("adtestpwd1");
//        adminVO1.setAdm_name("adtestname1");
//        adminVO1.setAdm_mail("adtestmail1");        
//        dao.insert(adminVO1);
//        System.out.println("新增OK");
       
        // 修改 OK
//        AdminVO adminVO2 = new AdminVO();
//        adminVO2.setAdm_no("ADM00001");
//        adminVO2.setAdm_acct("update");
//        adminVO2.setAdm_pwd("update");
//        adminVO2.setAdm_name("老老吳");
//        adminVO2.setAdm_mail("mail222@hotmail.com"); 
//        dao.update(adminVO2);
//        System.out.println("------修改---------");

        // 查詢 OK
//        AdminVO adminVO3 = dao.findByPrimaryKey("ADM00001");
//        System.out.print(adminVO3.getAdm_no() + ",");
//        System.out.print(adminVO3.getAdm_acct() + ",");
//        System.out.print(adminVO3.getAdm_pwd() + ",");
//        System.out.print(adminVO3.getAdm_name() + ",");
//        System.out.println(adminVO3.getAdm_mail());
//        System.out.println("---------------------");

        // 查詢全部 OK
//        List<AdminVO> list = dao.getAll();
//        for (AdminVO adminVO : list) {
//            System.out.print(adminVO.getAdm_no() + ",");
//            System.out.print(adminVO.getAdm_acct() + ",");
//            System.out.print(adminVO.getAdm_pwd() + ",");
//            System.out.print(adminVO.getAdm_name() + ",");
//            System.out.print(adminVO.getAdm_mail());
//            System.out.println();
//        }

    }

	@Override
	public AdminVO findByAdmAcct(String adm_acct) {
		// TODO Auto-generated method stub
		return null;
	}
}