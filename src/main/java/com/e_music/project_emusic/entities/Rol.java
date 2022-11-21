package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.e_music.project_emusic.enums.RolName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
