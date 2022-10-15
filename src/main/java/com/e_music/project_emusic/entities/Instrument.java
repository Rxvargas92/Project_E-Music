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
    private boolean active = true;

    @Column ( name = "name" )
    private String name;

    @Column ( name = "price" )
    private Double price;

    @Column ( name = "stock" )
    private Integer stock;

    @Column ( name = "pathImage" )
    private String pathImage;

    @Column ( name = "description" )
    private String description;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_cart", nullable = false )
    private Cart cart;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_brand", nullable = false )
    private Brand brand;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_category", nullable = false )
    private Category category;

}