package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
    public void insert(MemberVO memberVO);
    public void updateMember(MemberVO memberVO);
    public void updateExp(MemberVO memberVO);
    public void updateSenior(MemberVO memberVO);
    public void updateStop(MemberVO memberVO);
    public MemberVO findByAcct(String mem_acct);
    public MemberVO findByPrimaryKey(String mem_no);
    public List<MemberVO> getAll();
}
