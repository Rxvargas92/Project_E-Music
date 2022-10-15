package com.e_music.project_emusic.entities;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table ( name = "INSTRUMENT" )
@Audited
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instrument extends Base {

    @Column ( name = "active" )
    boolean active = true;

    @Column ( name = "name" )
    String name;

    @Column ( name = "price" )
    Double price;

    @Column ( name = "stock" )
    Integer stock;

    @Column ( name = "pathImage" )
    String pathImage;

    @Column ( name = "description" )
    String description;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_cart", nullable = false )
    Cart cart;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_brand", nullable = false )
    Brand brand;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_category", nullable = false )
    Category category;

}