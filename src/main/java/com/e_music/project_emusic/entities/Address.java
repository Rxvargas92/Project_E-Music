package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table ( name = "ADDRESS" )
@Audited
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