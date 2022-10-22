package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Entity
@Table(name = "USER")
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {

  @Column(name = "name")
  private String name;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "dni")
  private Integer dni;

  @Column(name = "admin")
  private boolean admin;

  @Column(name = "active")
  private boolean active = true;

  @Column(name = "userId")
  private String userId;

  @Column(name = "password")
  private String password;

  @OneToOne(fetch = FetchType.EAGER)
  @JsonManagedReference
  @JoinColumn(name = "fk_address", nullable = false)
  private Address address;


  @OneToOne(mappedBy = "user")
  @JsonBackReference
  private Cart cart;

}