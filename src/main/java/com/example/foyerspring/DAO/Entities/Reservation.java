package com.example.foyerspring.DAO.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idReservation;

    private Date anneeReservation ;
    private Boolean estValide ;

    //association with table Etudiant
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Etudiant> etudiants = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "chambre_id")
    @JsonIgnore
    private Chambre chambre;

}
