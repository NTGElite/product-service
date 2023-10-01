package com.demo.product.service.impl;

import com.demo.product.infrastructure.errorhandling.exception.NotFoundException;
import com.demo.product.model.entity.AbstractEntity;
import com.demo.product.service.BaseService;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This class will implement the {@link BaseService BaseService} class.
 *
 * @author duccaom
 * @version 1.0
 * @since 2023/10/01
 */
public class BaseServiceImpl<T extends AbstractEntity<? extends Serializable>, I extends  Serializable> implements BaseService<T, I> {

  private final JpaRepository<T, I> jpaRepository;

  public BaseServiceImpl(JpaRepository<T, I> jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public T findById(I id) {
    return jpaRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity not found"));
  }

  @Override
  public boolean existsById(I id) {
    boolean existed = jpaRepository.existsById(id);
    if (!existed) {
      throw new NotFoundException("Entity not found");
    }
    return true;
  }

  @Override
  public List<T> findAll() {
    return jpaRepository.findAll();
  }
}
