package com.example.foyerspring.Services;

import com.example.foyerspring.DAO.Entities.Chambre;
import com.example.foyerspring.DAO.Entities.Etudiant;
import com.example.foyerspring.DAO.Entities.Reservation;
import com.example.foyerspring.DAO.Entities.TypeChambre;
import com.example.foyerspring.DAO.Repositories.ChambreRepository;
import com.example.foyerspring.DAO.Repositories.EtudiantRepository;
import com.example.foyerspring.DAO.Repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Service
@AllArgsConstructor
public class ReservationService implements IReservationService{
    @Autowired
    ReservationRepository r;

    @Override
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) r.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return r.save(reservation);}

    @Override
    public Reservation findByIdReservation(String id) {
        return r.findByIdReservation(id);
    }
}
