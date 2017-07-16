package com.admin.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface Authority_FeatureDAO_interface {

    public void insert(Authority_FeatureVO authority_FeatureVO);

    public void update(Authority_FeatureVO authority_FeatureVO);

    public Authority_FeatureVO findByPrimaryKey(String auth_no);

    public List<Authority_FeatureVO> getAll();

}
