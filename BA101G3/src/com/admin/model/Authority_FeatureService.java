package com.admin.model;

import java.util.List;


public class Authority_FeatureService {

    private Authority_FeatureDAO_interface dao;

    public Authority_FeatureService() {
        dao = new Authority_FeatureDAO();
    }

    public Authority_FeatureVO addAuthority_Feature(String auth_no, String auth_name) {

        Authority_FeatureVO authority_FeatureVO = new Authority_FeatureVO();

        authority_FeatureVO.setAuth_name(auth_name);
        dao.insert(authority_FeatureVO);

        return authority_FeatureVO;
    }

    public Authority_FeatureVO updateAuthority_Feature(String auth_no, String auth_name) {

        Authority_FeatureVO authority_FeatureVO = new Authority_FeatureVO();

        authority_FeatureVO.setAuth_name(auth_name);
        authority_FeatureVO.setAuth_no(auth_no);

        dao.update(authority_FeatureVO);

        return authority_FeatureVO;
    }

    public Authority_FeatureVO getOneAF(String auth_no) {
        return dao.findByPrimaryKey(auth_no);
    }

    public List<Authority_FeatureVO> getAll() {
        return dao.getAll();
    }
}
