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

//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
//			Query query = session.createQuery("delete EmpVO where empno=?");
//			query.setParameter(0, empno);
//			System.out.println("�R��������=" + query.executeUpdate());

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
            Video video = new Video();
            video.setVidNo(vidNo);
            session.delete(video);

//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��emp2.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
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

        //�� �s�W
//		com.dept.model.DeptVO deptVO = new com.dept.model.DeptVO(); // ����POJO
//		deptVO.setDeptno(10);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("�d�ç�1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		dao.insert(empVO1);


        //�� �ק�
//		Member empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("�d�ç�7");
//		empVO2.setJob("MANAGER77");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(999));
//		empVO2.setComm(new Double(99));
//		empVO2.setDeptVO(deptVO);
//		dao.update(empVO2);


        //�� �R��(�p��cascade - �h��emp2.hbm.xml�p�G�]�� cascade="all"��
        // cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R��)
//		dao.delete(7014);


        //�� �d��-findByPrimaryKey (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
        Member member = dao.findByPrimaryKey("M0000001");
        System.out.print(member.getMemAcct() + ",");
        System.out.print(member.getMemNo() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
        // �`�N�H�U�T�檺�g�k (�u!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
        System.out.println("\n---------------------");


        //�� �d��-getAll (�h��emp2.hbm.xml�����]��lazy="false")(�u!)

//		dao.findByPrimaryKey(empno);
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// �`�N�H�U�T�檺�g�k (�u!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
    }

}
