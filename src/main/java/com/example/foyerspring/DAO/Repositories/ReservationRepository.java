package com.example.foyerspring.DAO.Repositories;

import com.example.foyerspring.DAO.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository <Reservation,String> {
    Reservation findByIdReservation(String idReserv);
}
