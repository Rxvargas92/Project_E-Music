package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
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
@Table(name = "BRAND")
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  Brand extends Base {

    private String name;
    private boolean active = true;

    @OneToMany(mappedBy = "brand")
    private List<Instrument> instruments;

}