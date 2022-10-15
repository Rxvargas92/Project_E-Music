package com.e_music.project_emusic.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "BRAND")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  Brand extends Base{

    String name;
    boolean active = true;

    @OneToMany(mappedBy = "brand")
    List<Instrument> instruments;

}