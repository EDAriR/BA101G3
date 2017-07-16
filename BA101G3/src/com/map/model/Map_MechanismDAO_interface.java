package com.map.model;

import java.util.*;

public interface Map_MechanismDAO_interface {
	      public void insert(Map_MechanismVO map_mechanismVO);
          public void update(Map_MechanismVO map_mechanismVO);
          public void delete(String map_no);
          public Map_MechanismVO findByPrimaryKey(String map_no);
	      public List<Map_MechanismVO> getAll();
}
