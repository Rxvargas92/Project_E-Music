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

@Entity
@Table(name = "BRAND")
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  Brand extends Base {

    @Column(name = "name")
    private String name;

    private boolean active = true;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference(value = "brand-instruments")
    private List<Instrument> instruments;

}