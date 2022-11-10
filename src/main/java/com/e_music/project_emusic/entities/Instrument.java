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
import javax.validation.Valid;
import javax.validation.constraints.*;
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

  @NotEmpty(message = "The name cannot be empty")
  @Column(name = "name")
  private String name;

  @Max(value= 1000000, message = "Price cannot be greater than 1000000")
  @Positive(message = "Stock must be positive")
  @Digits(integer = 7, fraction = 2)
  @Column(name = "price")
  private Double price;

  @Max(value= 10000, message = "Stock cannot be greater than 10000")
  @Positive(message = "Stock must be positive")
  @Column(name = "stock")
  private Integer stock;

  @Column(name = "pathImage")
  private String pathImage;

  @NotNull(message = "description cannot be null")
  @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
  @Column(name = "description")
  private String description;

  @OneToOne(mappedBy = "instrument")
  @JsonBackReference
  private Cart cart;
  

  @NotNull(message = "Brand cannot be null")
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JsonBackReference(value = "brand-instruments")
  @JoinColumn(name = "fk_brand", nullable = false)
  private Brand brand;

  @NotNull(message = "Category cannot be null")
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JsonBackReference(value = "category-instruments")
  @JoinColumn(name = "fk_category", nullable = false)
  private Category category;

}