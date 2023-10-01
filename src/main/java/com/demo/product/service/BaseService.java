package com.demo.product.service;

import com.demo.product.model.entity.AbstractEntity;
import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends AbstractEntity<? extends Serializable>, I extends Serializable> {
  T findById(I id);

  boolean existsById(I id);

  List<T> findAll();
}
