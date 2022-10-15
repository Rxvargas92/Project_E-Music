package com.e_music.project_emusic.entities;

import lombok.*;
import com.e_music.project_emusic.entities.User;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table ( name = "CART" )
@Audited
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Base {

    //Comentario random
    @Column(name = "paymentMethod")
    private String payment_method;

    @Column(name = "total_price")
    private Double total_price;

    @Column( name = "active")
    private boolean active;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "fk_user", nullable = false)
    private User user;

    @OneToMany( fetch = FetchType.EAGER )
    @JoinColumn( name = "fk_instrument", nullable = false)
    private List<Instrument> instruments;

}