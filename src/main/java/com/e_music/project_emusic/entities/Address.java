package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.beans.XMLEncoder;
import java.util.List;

@Entity
@Table ( name = "ADDRESS" )
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Base {

    @NotEmpty(message = "Street cannot be null")
    @Column ( name = "street" )
    private String street;

    @Min(value = 1, message = "DNI cannot be less than 1")
    @Max(value = 99999, message = "DNI cannot be greater than 99999")
    @Column ( name = "number" )
    private Integer number;

    @Min(value = 0, message = "DNI cannot be less than 0")
    @Max(value = 9999, message = "DNI cannot be greater than 9999")
    @Column ( name = "postalCode" )
    private Integer postalCode;

    @NotEmpty(message = "Location cannot be null")
    @Column ( name = "location" )
    private String location;

    @NotEmpty(message = "Province cannot be null")
    @Column ( name = "province" )
    private String province;


}