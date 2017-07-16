package com.admin.model;

import java.util.List;


public class AdminService {

    private AdminDAO_interface dao;

    public AdminService() {
        dao = new AdminDAO();
    }

    public AdminVO addAdmin(String adm_acct, String adm_pwd,
                                  String adm_name, String adm_mail) {

        AdminVO adminVO = new AdminVO();

//      adm_no, adm_acct, adm_pwd, adm_name,adm_mail
        adminVO.setAdm_acct(adm_acct);
        adminVO.setAdm_pwd(adm_pwd);
        adminVO.setAdm_name(adm_name);
        adminVO.setAdm_mail(adm_mail);
        dao.insert(adminVO);

        return adminVO;
    }

    public AdminVO updateAdmin(String adm_no, String adm_acct,
                               String adm_pwd, String adm_name,
                               String adm_mail) {

        AdminVO adminVO = new AdminVO();

        adminVO.setAdm_acct(adm_acct);
        adminVO.setAdm_pwd(adm_pwd);
        adminVO.setAdm_name(adm_name);
        adminVO.setAdm_mail(adm_mail);
        adminVO.setAdm_no(adm_no);

        dao.update(adminVO);

        return adminVO;
    }

    public AdminVO getOneAD(String adm_no) {
        return dao.findByPrimaryKey(adm_no);
    }

    public AdminVO getOneAdmin(String adm_acct) { return dao.findByAdmAcct(adm_acct); }

    public List<AdminVO> getAll() {
        return dao.getAll();
    }
}
