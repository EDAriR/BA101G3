package com.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Expert_ClassificationJDBCDAO implements Expert_ClassificationDAO_interface {

    String DRIVER = "oracle.jdbc.driver.OracleDriver";
    String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    String USER = "ba101g3";
    String PASSWORD = "baby";

    private static final String INSERT_STMT = "INSERT INTO expert_classification (exp_no,exp_cname) VALUES ('E'||LPAD(to_char(exp_no_seq.NEXTVAL), 3, '0'), ?)";
    private static final String UPDATE = "UPDATE expert_classification SET exp_cname=? WHERE exp_no = ?";

    private static final String GET_ALL_STMT = "SELECT exp_no, exp_cname FROM expert_classification ORDER BY exp_no";
    private static final String GET_ONE_STMT = "SELECT exp_no, exp_cname FROM expert_classification WHERE exp_no = ?";


    @Override
    public void insert(Expert_ClassificationVO expert_classificationVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String[] cols = {"exp_no"}; // 有使用sequence產生編號的話才要寫
            pstmt = con.prepareStatement(INSERT_STMT, cols); // 有使用sequence產生編號的話才要寫第二個參數
            pstmt.setString(1, expert_classificationVO.getExp_cname());
            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
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
    public void update(Expert_ClassificationVO expert_classificationVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, expert_classificationVO.getExp_cname());
            pstmt.setString(2, expert_classificationVO.getExp_no());

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
    public List<Expert_ClassificationVO> getAll() {
        List<Expert_ClassificationVO> list = new ArrayList<Expert_ClassificationVO>();
        Expert_ClassificationVO expert_classificationVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                // expert_classificationVO 也稱為 Domain objects
                expert_classificationVO = new Expert_ClassificationVO();
                expert_classificationVO.setExp_no(rs.getString("exp_no"));
                expert_classificationVO.setExp_cname(rs.getString("exp_cname"));

                list.add(expert_classificationVO); // Store the row in the list
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
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
    public Expert_ClassificationVO findByPrimaryKey(String exp_no) {

        Expert_ClassificationVO expert_classificationVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, exp_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                expert_classificationVO = new Expert_ClassificationVO();
                expert_classificationVO.setExp_no(rs.getString("exp_no"));
                expert_classificationVO.setExp_cname(rs.getString("exp_cname"));

            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
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
        return expert_classificationVO;
    }


    public static void main(String[] args) {

        Expert_ClassificationJDBCDAO dao = new Expert_ClassificationJDBCDAO();

//		// 新增
//    	Expert_ClassificationVO expc1 = new Expert_ClassificationVO();
//		expc1.setExp_no("0006");
//		expc1.setExp_cname("兒童教育專家");
//		dao.insert(expc1);

//		// 修改
//		Expert_ClassificationVO expc2 = new Expert_ClassificationVO();
//		expc2.setExp_no("0002");
//		expc2.setExp_cname("超級奶爸");
//		System.out.println("資料已修改");
//		dao.update(expc2);
//		System.out.println("資料已修改");
//

//		// 查詢
//		Expert_ClassificationVO expc3 = dao.findByPrimaryKey("0002");
//		System.out.print(expc3.getExp_no() + ",");
//		System.out.print(expc3.getExp_cname());
//		System.out.println("---------------------");

        // 查詢
        List<Expert_ClassificationVO> list = dao.getAll();
        for (Expert_ClassificationVO exp : list) {
            System.out.print(exp.getExp_no() + ",");
            System.out.print(exp.getExp_cname());
            System.out.println();
        }
    }

}
