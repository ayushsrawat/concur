package com.github.concur.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private ProductCategory category;

  @Column(name = "name", nullable = false, unique = true, length = 100)
  private String name;

  private String description;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "image_url")
  private String imageUrl;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductAttribute> attributes = new ArrayList<>();

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime cratedAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public void addAttribute(ProductAttribute attribute) {
    attributes.add(attribute);
    attribute.setProduct(this);
  }

  public void removeAttribute(ProductAttribute attribute) {
    attributes.remove(attribute);
    attribute.setProduct(null);
  }

}
