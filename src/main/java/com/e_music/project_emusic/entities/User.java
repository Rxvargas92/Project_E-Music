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
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
  private Set<Rol> roles = new HashSet<>();


  @Email(message = "This field must have mail format")
  @Column(name = "email", unique = true)
  private String email;


  @Column(name = "password")
  private String password;


  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonManagedReference
  @JoinColumn(name = "fk_address", nullable = true)
  private Address address;

  public User(String name, String lastName, Integer dni, String email, String password, Address address) {
    this.name = name;
    this.lastName = lastName;
    this.dni = dni;
    this.email = email;
    this.password = password;
    this.address = address;
  }
}