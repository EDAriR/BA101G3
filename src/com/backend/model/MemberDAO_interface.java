package com.backend.model;

import java.util.*;

public interface MemberDAO_interface {
          public void insert(Member member);
          public void update(Member member);
//          public void delete(String mem_no);
          public Member findByPrimaryKey(String mem_no);
          public List<Member> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
