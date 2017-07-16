package com.news.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface NewsDAO_interface {

    public void insert(NewsVO NewsVO);
    public void update(NewsVO NewsVO);
    public void delete(String new_no);
    public NewsVO findByPrimaryKey(String new_no);
    public List<NewsVO> getAll();

}
