package com.e_music.project_emusic.entities;

import lombok.*;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Base{

    private String name;
    private boolean active = true;

    @OneToMany(mappedBy = "category")
    private List<Instrument> instruments;

}