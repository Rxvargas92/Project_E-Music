package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
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

  @NotEmpty(message = "The name cannot be empty")
  @Column(name = "name")
  private String name;

  @NotEmpty(message = "The Last name cannot be empty")
  @Column(name = "lastName")
  private String lastName;

  @Min(value = 1000000, message = "DNI cannot be less than 1000000")
  @Max(value= 99999999, message = "DNI cannot be greater than 99999999")
  @Column(name = "dni")
  private Integer dni;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
  )
  private Set<Rol> roles = new HashSet<>();


  @NotEmpty(message = "The Email cannot be empty")
  @Email(message = "This field must have mail format")
  @Column(name = "email", unique = true)
  private String email;

  @NotEmpty(message = "Password cannot be empty")
  @Column(name = "password")
  private String password;


  @OneToOne(fetch = FetchType.EAGER)
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