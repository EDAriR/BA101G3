package com.member.model;

import java.util.List;

public interface Expert_ClassificationDAO_interface {
	  public void insert(Expert_ClassificationVO expert_classificationVO);
      public void update(Expert_ClassificationVO expert_classificationVO);
      public Expert_ClassificationVO findByPrimaryKey(String exp_no);
      public List<Expert_ClassificationVO> getAll();
}
