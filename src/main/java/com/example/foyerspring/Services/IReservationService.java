package com.example.foyerspring.Services;

import com.example.foyerspring.DAO.Entities.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();

    Reservation addReservation (Reservation r );


    Reservation findByIdReservation(String id);

}
