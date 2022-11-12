package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.e_music.project_emusic.enums.RolName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "ROL")
@Audited
@NoArgsConstructor
@Getter
@Setter
public class Rol extends Base {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name ="rolname", unique = true)
    private RolName rolName;

    public Rol(RolName rolName) {
        this.rolName = rolName;
    }
}
