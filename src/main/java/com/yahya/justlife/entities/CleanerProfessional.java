package com.yahya.justlife.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name ="cleaner_professional")
public class CleanerProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private String surname;

    private double rating ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference()
    private Car car;

    @ManyToMany(mappedBy = "cleanerProfessionalsOfReservation",fetch = FetchType.LAZY)
    @JsonBackReference()
    private List<Reservation> reservationList;

}
