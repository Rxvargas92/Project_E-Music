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

    @Column(name = "paymentMethod")
    String payment_method;

    @Column(name = "total_price")
    Double total_price;

    @Column( name = "active")
    boolean active;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "fk_user", nullable = false)
    User user;

    @OneToMany( fetch = FetchType.EAGER )
    @JoinColumn( name = "fk_instrument", nullable = false)
    List<Instrument> instruments;

}