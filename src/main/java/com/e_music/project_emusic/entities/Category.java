package com.e_music.project_emusic.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Base{

    String name;
    boolean active = true;

    @OneToMany(mappedBy = "category")
    List<Instrument> instruments;

}