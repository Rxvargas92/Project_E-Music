package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROL")
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol extends Base {

    @Column(name = "type")
    private String type;

}
