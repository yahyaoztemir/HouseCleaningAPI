package com.yahya.justlife.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name ="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Date date ;

    private LocalTime startTime ;

    private LocalTime endTime;

    private int duration ;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "cleanerprofessionalsofreservations",
            joinColumns = { @JoinColumn(name = "reservation_id") },
            inverseJoinColumns = { @JoinColumn(name = "cleaner_professional_id") })
    private List<CleanerProfessional> cleanerProfessionalsOfReservation ;
}
