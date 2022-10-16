package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "CATEGORY")
@Audited
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Base {

  @Column(name = "name")
  private String name;

  private boolean active = true;

  @OneToMany(mappedBy = "category")
  private List<Instrument> instruments;

}