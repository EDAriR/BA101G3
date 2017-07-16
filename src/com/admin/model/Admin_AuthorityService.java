package com.admin.model;

import java.util.List;


public class Admin_AuthorityService {

    private Admin_AuthorityDAO_interface dao;

    public Admin_AuthorityService() { dao = new Admin_AuthorityDAO(); }

    public Admin_AuthorityVO addChat_Friend(String adm_no, String auth_no) {

        Admin_AuthorityVO admin_AuthorityVO = new Admin_AuthorityVO();

        admin_AuthorityVO.setAdm_no(adm_no);
        admin_AuthorityVO.setAuth_no(auth_no);
        dao.insert(admin_AuthorityVO);

        return admin_AuthorityVO;
    }

    public void deleteAA(String adm_no, String auth_no) {
        dao.delete(adm_no, auth_no);
    }

    public Admin_AuthorityVO getByPk(String adm_no,String auth_no) { return dao.findByPrimaryKey(adm_no, auth_no); }

    public List<Admin_AuthorityVO> getByAdminNo(String auth_no) { return dao.findByAuthNo(auth_no); }

    public List<Admin_AuthorityVO> getByAuthNo(String adm_no) { return dao.findByAdmNo(adm_no); }

    public List<Admin_AuthorityVO> getAll() {
        return dao.getAll();
    }

}
