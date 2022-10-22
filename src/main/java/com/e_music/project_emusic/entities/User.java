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
import java.util.List;


@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
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

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_rols",
  joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
  private List<Rol> rols;

  @Column(name = "active")
  private boolean active = true;

  @Column(name = "email")
  private String email;

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