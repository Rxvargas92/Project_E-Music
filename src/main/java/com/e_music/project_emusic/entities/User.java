package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "USER")
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class User extends Base {

  @Column(name = "name")
  private String name;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "dni")
  private Integer dni;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
  )
  private Collection<Rol> roles;

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