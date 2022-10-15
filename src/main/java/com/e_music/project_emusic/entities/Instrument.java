package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INSTRUMENT")
//@Audited
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instrument extends Base {

  @Column(name = "active")
  private boolean active = true;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Double price;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "pathImage")
  private String pathImage;

  @Column(name = "description")
  private String description;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_cart", nullable = false)
  private Cart cart;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_brand", nullable = false)
  private Brand brand;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_category", nullable = false)
  private Category category;

}