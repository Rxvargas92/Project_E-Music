package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table ( name = "ADDRESS" )
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Base {

    @Column ( name = "street" )
    private String street;

    @Column ( name = "number" )
    private Integer number;

    @Column ( name = "postalCode" )
    private Integer postalCode;

    @Column ( name = "location" )
    private String location;

    @Column ( name = "province" )
    private String province;

    @OneToOne( mappedBy = "address" )
    @JsonBackReference
    private User user;

}