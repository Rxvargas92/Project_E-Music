package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "CART")
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Base {

  @Column(name = "paymentMethod")
  private String payment_method;

  @Column(name = "total_price")
  private Double total_price;

  @Column(name = "active")
  private boolean active;


  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user", nullable = false)
  private User user;


  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_instrument", nullable = false)
  private Instrument instrument;


}