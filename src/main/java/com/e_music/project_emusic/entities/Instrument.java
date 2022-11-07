package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;


import javax.persistence.*;
import java.util.List;


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
  @JsonBackReference
  private Cart cart;
  

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JsonBackReference(value = "brand-instruments")
  @JoinColumn(name = "fk_brand", nullable = false)
  private Brand brand;


  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JsonBackReference(value = "category-instruments")
  @JoinColumn(name = "fk_category", nullable = false)
  private Category category;

}