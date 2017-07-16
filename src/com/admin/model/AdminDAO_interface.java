package com.admin.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface AdminDAO_interface {

    public void insert(AdminVO AdminVO);

    public void update(AdminVO AdminVO);

    public AdminVO findByPrimaryKey(String adm_no);

    AdminVO findByAdmAcct(String adm_acct);

    public List<AdminVO> getAll();

}
