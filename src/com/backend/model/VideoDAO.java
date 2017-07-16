package com.backend.model;

import hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class VideoDAO implements VideoDAO_interface {

    private static final String GET_ALL_STMT = "from Video order by vidNo";

    @Override
    public void insert(Video video) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(video);
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void update(Video video) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(video);
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
    }

    public void delete(String vidNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
            Video video = new Video();
            video.setVidNo(vidNo);
            session.delete(video);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public Video findByPrimaryKey(String vidNo) {
        Video video = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            video = (Video) session.get(Video.class, vidNo);
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return video;
    }

//    @Override
    public Video findByMemNo(String memNo) {
        Video video = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            video = (Video) session.get(Video.class, memNo);
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return video;
    }

    @Override
    public List<Video> getAll() {
        List<Video> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(GET_ALL_STMT);
            list = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return list;
    }

    public static void main(String[] args) {

        MemberDAO dao = new MemberDAO();

        //● 新增
//		com.dept.model.DeptVO deptVO = new com.dept.model.DeptVO(); // 部門POJO
//		deptVO.setDeptno(10);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		dao.insert(empVO1);


        //● 修改
//		Member empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志7");
//		empVO2.setJob("MANAGER77");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(999));
//		empVO2.setComm(new Double(99));
//		empVO2.setDeptVO(deptVO);
//		dao.update(empVO2);


        //● 刪除(小心cascade - 多方emp2.hbm.xml如果設為 cascade="all"或
        // cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除)
//		dao.delete(7014);


        //● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
        Member member = dao.findByPrimaryKey("M0000001");
        System.out.print(member.getMemAcct() + ",");
        System.out.print(member.getMemNo() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
        // 注意以下三行的寫法 (優!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
        System.out.println("\n---------------------");


        //● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)

//		dao.findByPrimaryKey(empno);
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
    }

}
