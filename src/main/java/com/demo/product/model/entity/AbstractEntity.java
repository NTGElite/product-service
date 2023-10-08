package com.demo.product.model.entity;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Base entity class.
 *
 * @author duccaom
 * @version 1.0
 * @since 2023/10/01
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AbstractEntity<I extends Serializable> {

  public abstract I getId();

  @CreatedDate
  @Column(name = "created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "modified_at")
  private Instant modifiedAt;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Version
  @Column(name = "version", nullable = false, columnDefinition = "int default 0")
  private Integer version;
}
