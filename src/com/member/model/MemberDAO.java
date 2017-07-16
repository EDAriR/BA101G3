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

public class MemberDAO implements MemberDAO_interface {

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

    // 新增資料(會員註冊)
    private static final String INSERT_STMT = "INSERT INTO member (mem_no, memg_gr, mem_acct, mem_pwd, mem_joind, mem_name, "
            + "mem_adds, mem_is_sell, mem_is_stop) VALUES ('M'||LPAD(to_char(mem_no_seq.NEXTVAL), 7, '0'), '0', ?, ?,"
            + "SYSDATE, ?, ?, '0', '0')";
    // 修改會員資料
    private static final String UPDATE_BASIC = "UPDATE member SET mem_pwd=?, mem_name=?, mem_aka=?, mem_photo=?, mem_adds=?,"
            + "mem_addc=?, mem_phone=?, mem_mail=?, mem_intro_b=?, mem_acct_s=?, mem_intro_s=? WHERE mem_no =?";

    // 查詢資料
    private static final String GET_ONE_AUTHO_STMT = "SELECT * FROM member WHERE mem_acct=?";
    private static final String GET_ALL_STMT = "SELECT * FROM member";
    private static final String GET_ONE_STMT = "SELECT * FROM member WHERE mem_no = ?";

    //專家申請:: 會員等級編號memg_gr='3'審核中  exp_no:專家分類編號 mem_intro_e:專家/資深爸爸媽媽基本介紹=
    //專家任職地區=註冊地址adds, addc
    private static final String UPDATE_EXP = "UPDATE member SET mem_updated=SYSDATE, " +
            "memg_gr='3', mem_adds=?, mem_addc=?, exp_no=?, mem_intro_e=? WHERE mem_no = ?";
    //資深爸爸媽媽申請
    private static final String UPDATE_SENIOR = "UPDATE member SET mem_updated=SYSDATE, " +
            "memg_gr='3', mem_intro_e=? WHERE mem_no = ?";

    private static final String UPDATE_REPORT = "UPDATE member SET " +
            "mem_is_stop=? WHERE mem_no = ?";


    @Override
    public void insert(MemberVO memberVO) { //register

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, memberVO.getMem_acct());
            pstmt.setString(2, memberVO.getMem_pwd());
            pstmt.setString(3, memberVO.getMem_name());
            pstmt.setString(4, memberVO.getMem_adds());

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
    public MemberVO findByAcct(String mem_acct) { //login

        MemberVO memberVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_AUTHO_STMT);
            pstmt.setString(1, mem_acct);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                memberVO = new MemberVO();
                memberVO.setMem_no(rs.getString("mem_no"));
                memberVO.setExp_no(rs.getString("exp_no"));
                memberVO.setMemg_gr(rs.getString("memg_gr"));
                memberVO.setMem_acct(rs.getString("mem_acct"));
                memberVO.setMem_pwd(rs.getString("mem_pwd"));
                memberVO.setMem_joind(rs.getDate("mem_joind"));
                memberVO.setMem_updated(rs.getDate("mem_updated"));
                memberVO.setMem_name(rs.getString("mem_name"));
                memberVO.setMem_aka(rs.getString("mem_aka"));
                memberVO.setMem_photo(rs.getBytes("mem_photo"));
                memberVO.setMem_adds(rs.getString("mem_adds"));
                memberVO.setMem_addc(rs.getString("mem_addc"));
                memberVO.setMem_phone(rs.getString("mem_phone"));
                memberVO.setMem_mail(rs.getString("mem_mail"));
                memberVO.setMem_intro_b(rs.getString("mem_intro_b"));
                memberVO.setMem_is_sell(rs.getString("mem_is_sell"));
                memberVO.setMem_acct_s(rs.getString("mem_acct_s"));
                memberVO.setMem_intro_s(rs.getString("mem_intro_s"));
                memberVO.setMem_point_s(rs.getInt("mem_point_s"));
                memberVO.setMem_intro_e(rs.getString("mem_intro_e"));
                memberVO.setMem_is_stop(rs.getString("mem_is_stop"));
                memberVO.setMem_point_b(rs.getInt("mem_point_b"));
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
        return memberVO;
    }

    @Override
    public void updateExp(MemberVO memberVO) { //UPDATE EXPERT

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE_EXP);
            pstmt.setString(1, memberVO.getMem_adds());
            pstmt.setString(2, memberVO.getMem_addc());
            pstmt.setString(3, memberVO.getExp_no());
            pstmt.setString(4, memberVO.getMem_intro_e());
            pstmt.setString(5, memberVO.getMem_no());
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
    public void updateSenior(MemberVO memberVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE_SENIOR);
            pstmt.setString(1, memberVO.getMem_intro_e());
            pstmt.setString(2, memberVO.getMem_no());
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
    public void updateStop(MemberVO memberVO) { //UPDATE EXPERT

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE_REPORT);
            pstmt.setString(1, memberVO.getMem_is_stop());
            pstmt.setString(2, memberVO.getMem_no());
            pstmt.executeUpdate();
            System.out.println("memberVO.getMem_is_stop()===============??????????" + memberVO.getMem_is_stop());

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
    public void updateMember(MemberVO memberVO) { //member update

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE_BASIC);
            pstmt.setString(1, memberVO.getMem_pwd());
            pstmt.setString(2, memberVO.getMem_name());
            pstmt.setString(3, memberVO.getMem_aka());
            pstmt.setBytes(4, memberVO.getMem_photo());
            pstmt.setString(5, memberVO.getMem_adds());
            pstmt.setString(6, memberVO.getMem_addc());
            pstmt.setString(7, memberVO.getMem_phone());
            pstmt.setString(8, memberVO.getMem_mail());
            pstmt.setString(9, memberVO.getMem_intro_b());
            pstmt.setString(10, memberVO.getMem_acct_s());
            pstmt.setString(11, memberVO.getMem_intro_s());
            pstmt.setString(12, memberVO.getMem_no());
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
    public MemberVO findByPrimaryKey(String mem_no) {

        MemberVO memberVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, mem_no);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memberVO = new MemberVO();
                memberVO.setMem_no(rs.getString("mem_no"));
                memberVO.setExp_no(rs.getString("exp_no"));
                memberVO.setMemg_gr(rs.getString("memg_gr"));
                memberVO.setMem_acct(rs.getString("mem_acct"));
                memberVO.setMem_pwd(rs.getString("mem_pwd"));
                memberVO.setMem_joind(rs.getDate("mem_joind"));
                memberVO.setMem_updated(rs.getDate("mem_updated"));
                memberVO.setMem_name(rs.getString("mem_name"));
                memberVO.setMem_aka(rs.getString("mem_aka"));
                memberVO.setMem_photo(rs.getBytes("mem_photo"));
                memberVO.setMem_adds(rs.getString("mem_adds"));
                memberVO.setMem_addc(rs.getString("mem_addc"));
                memberVO.setMem_phone(rs.getString("mem_phone"));
                memberVO.setMem_mail(rs.getString("mem_mail"));
                memberVO.setMem_intro_b(rs.getString("mem_intro_b"));
                memberVO.setMem_is_sell(rs.getString("mem_is_sell"));
                memberVO.setMem_acct_s(rs.getString("mem_acct_s"));
                memberVO.setMem_intro_s(rs.getString("mem_intro_s"));
                memberVO.setMem_point_s(rs.getInt("mem_point_s"));
                memberVO.setMem_intro_e(rs.getString("mem_intro_e"));
                memberVO.setMem_is_stop(rs.getString("mem_is_stop"));
                memberVO.setMem_point_b(rs.getInt("mem_point_b"));
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
        return memberVO;
    }

    @Override
    public List<MemberVO> getAll() {

        List<MemberVO> list = new ArrayList<MemberVO>();
        MemberVO memberVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                memberVO = new MemberVO();
                memberVO.setMem_no(rs.getString("mem_no"));
                memberVO.setExp_no(rs.getString("exp_no"));
                memberVO.setMemg_gr(rs.getString("memg_gr"));
                memberVO.setMem_acct(rs.getString("mem_acct"));
                memberVO.setMem_pwd(rs.getString("mem_pwd"));
                memberVO.setMem_joind(rs.getDate("mem_joind"));
                memberVO.setMem_updated(rs.getDate("mem_updated"));
                memberVO.setMem_name(rs.getString("mem_name"));
                memberVO.setMem_aka(rs.getString("mem_aka"));
                memberVO.setMem_photo(rs.getBytes("mem_photo"));
                memberVO.setMem_adds(rs.getString("mem_adds"));
                memberVO.setMem_addc(rs.getString("mem_addc"));
                memberVO.setMem_phone(rs.getString("mem_phone"));
                memberVO.setMem_mail(rs.getString("mem_mail"));
                memberVO.setMem_intro_b(rs.getString("mem_intro_b"));
                memberVO.setMem_is_sell(rs.getString("mem_is_sell"));
                memberVO.setMem_acct_s(rs.getString("mem_acct_s"));
                memberVO.setMem_intro_s(rs.getString("mem_intro_s"));
                memberVO.setMem_point_s(rs.getInt("mem_point_s"));
                memberVO.setMem_intro_e(rs.getString("mem_intro_e"));
                memberVO.setMem_is_stop(rs.getString("mem_is_stop"));
                memberVO.setMem_point_b(rs.getInt("mem_point_b"));
                list.add(memberVO); // Store the row in the list
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
