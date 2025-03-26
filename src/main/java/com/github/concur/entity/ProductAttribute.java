package com.github.concur.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_attributes")
@Getter
@Setter
public class ProductAttribute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "attribute_name", nullable = false, length = 50)
  private String attributeName;

  @Column(name = "attribute_value", nullable = false)
  private String attributeValue;

  @Column(name = "attribute_type", nullable = false, length = 20)
  private String attributeType;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime cratedAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

}
