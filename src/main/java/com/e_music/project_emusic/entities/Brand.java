package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "BRAND")
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  Brand extends Base {

    @NotEmpty(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference(value = "brand-instruments")
    private List<Instrument> instruments;

}