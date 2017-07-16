package com.admin.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface Admin_AuthorityDAO_interface {

    public void insert(Admin_AuthorityVO Admin_AuthorityVO);

    public void delete(String adm_no, String auth_no);

    public Admin_AuthorityVO findByPrimaryKey(String adm_no, String auth_no);

    public List<Admin_AuthorityVO> findByAdmNo(String adm_no);

    public List<Admin_AuthorityVO> findByAuthNo(String auth_no);

    public List<Admin_AuthorityVO> getAll();

}
