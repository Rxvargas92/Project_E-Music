package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;


import javax.persistence.*;


@Entity
@Table(name = "INSTRUMENT")
@Audited
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

  @OneToOne(mappedBy = "instrument")
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