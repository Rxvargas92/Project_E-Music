package com.e_music.project_emusic.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table ( name = "ADDRESS" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column ( name = "active" )
    private boolean active = true;

    @OneToMany ( mappedBy = "address" )
    private List<User> users;

}