package com.member.model;

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

public class Expert_ClassificationDAO implements Expert_ClassificationDAO_interface {

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
    private static final String INSERT_STMT = "INSERT INTO expert_classification (exp_no,exp_cname) VALUES ('E'||LPAD(to_char(exp_no_seq.NEXTVAL), 3, '0'), ?)";
    // 修改資料
    private static final String UPDATE = "UPDATE expert_classification SET exp_cname=? WHERE exp_no = ?";
    // 查詢資料
    private static final String GET_ALL_STMT = "SELECT exp_no, exp_cname FROM expert_classification ORDER BY exp_no";
    private static final String GET_ONE_STMT = "SELECT exp_no, exp_cname FROM expert_classification WHERE exp_no = ?";

    @Override
    public void insert(Expert_ClassificationVO expert_classificationVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, expert_classificationVO.getExp_cname());
            pstmt.executeUpdate();


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

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, expert_classificationVO.getExp_cname());
            pstmt.setString(2, expert_classificationVO.getExp_no());

            pstmt.executeUpdate();
            // Handle any driver errors
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
    public Expert_ClassificationVO findByPrimaryKey(String exp_no) {

        Expert_ClassificationVO expert_classificationVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, exp_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                expert_classificationVO = new Expert_ClassificationVO();
                expert_classificationVO.setExp_no(rs.getString("exp_no"));
                expert_classificationVO.setExp_cname(rs.getString("exp_cname"));

            }
            // Handle any driver errors
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

    @Override
    public List<Expert_ClassificationVO> getAll() {

        List<Expert_ClassificationVO> list = new ArrayList<Expert_ClassificationVO>();
        Expert_ClassificationVO expert_classificationVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
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
}
