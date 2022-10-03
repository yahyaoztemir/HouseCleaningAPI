package com.yahya.justlife.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name ="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String numberPlate ;

    private String brand ;

    private String model ;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<CleanerProfessional> cleanerProfessionalList;

}
