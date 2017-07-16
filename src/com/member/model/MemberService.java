package com.member.model;

import java.util.List;

public class MemberService {

    private MemberDAO_interface dao;

    public MemberService() {
        dao = new MemberDAO();
    }

    public MemberVO addMember(String mem_acct, String mem_pwd, String mem_name, String mem_adds) {

        MemberVO memberVO = new MemberVO();
        memberVO.setMem_acct(mem_acct);
        memberVO.setMem_pwd(mem_pwd);
        memberVO.setMem_name(mem_name);
        memberVO.setMem_adds(mem_adds);

        dao.insert(memberVO);
        return memberVO;
    }

    public MemberVO updateMember(
            String mem_pwd, String mem_name, String mem_aka, byte[] mem_photo,
            String mem_adds, String mem_addc, String mem_phone, String mem_mail,
            String mem_intro_b, String mem_acct_s, String mem_intro_s, String mem_no) {

        MemberVO memberVO = new MemberVO();
        memberVO.setMem_pwd(mem_pwd);
        memberVO.setMem_name(mem_name);
        memberVO.setMem_aka(mem_aka);
        memberVO.setMem_photo(mem_photo);
        memberVO.setMem_adds(mem_adds);
        memberVO.setMem_addc(mem_addc);
        memberVO.setMem_phone(mem_phone);
        memberVO.setMem_mail(mem_mail);
        memberVO.setMem_intro_b(mem_intro_b);
        memberVO.setMem_acct_s(mem_acct_s);
        memberVO.setMem_intro_s(mem_intro_s);
        memberVO.setMem_no(mem_no);
        dao.updateMember(memberVO);
        return memberVO;
    }

    public MemberVO updateExpert(String mem_adds, String mem_addc, String exp_no, String mem_intro_e, String mem_no) {
        MemberVO memberVO = new MemberVO();
        memberVO.setMem_adds(mem_adds);
        memberVO.setMem_addc(mem_addc);
        memberVO.setExp_no(exp_no);
        memberVO.setMem_intro_e(mem_intro_e);
        memberVO.setMem_no(mem_no);
        dao.updateExp(memberVO);
        return memberVO;
    }

    public MemberVO updateSenior(String mem_intro_e, String mem_no) {
        MemberVO memberVO = new MemberVO();
        memberVO.setMem_intro_e(mem_intro_e);
        memberVO.setMem_no(mem_no);
        dao.updateExp(memberVO);
        return memberVO;
    }

    public MemberVO updateStop(String mem_is_stop, String mem_no) {

        MemberVO memberVO = new MemberVO();
        memberVO.setMem_is_stop(mem_is_stop);
        memberVO.setMem_no(mem_no);
        dao.updateStop(memberVO);
        System.out.println("memberVO.setMem_is_stop(mem_is_stop);===============??????????" + memberVO.getMem_is_stop());

        return memberVO;

    }

    public MemberVO getOneMember(String mem_no) {
        return dao.findByPrimaryKey(mem_no);
    }

    public MemberVO getOneMemberByAcct(String mem_acct) {
        return dao.findByAcct(mem_acct);
    }

    public List<MemberVO> getAll() {
        return dao.getAll();
    }

}
