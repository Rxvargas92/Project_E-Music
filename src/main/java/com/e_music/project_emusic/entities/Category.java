package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Base {

  @Column(name = "name")
  private String name;

  private boolean active = true;

  @OneToMany(mappedBy = "category")
  @JsonManagedReference(value = "category-instruments")
  private List<Instrument> instruments;

}