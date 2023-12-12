package com.example.foyerspring.Services;

import com.example.foyerspring.DAO.Entities.Etudiant;
import com.example.foyerspring.DAO.Entities.Reservation;
import com.example.foyerspring.DAO.Repositories.EtudiantRepository;
import com.example.foyerspring.DAO.Repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Builder
@Service
@AllArgsConstructor
public class EtudiantService implements IEtudiantService {

    @Autowired
    public EtudiantRepository e;
    @Autowired
    ReservationRepository r;

    @Override
    public Long addEtudiant(Etudiant etudiant) {
        return e.save(etudiant).getIdEtudiant();
    }

    @Override
    public List<Etudiant> addAllEtudiant(List<Etudiant> liste) {
        return e.saveAll(liste);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return e.findAll();
    }

    @Override
    public Etudiant findById(Long id) {
        return e.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        e.deleteById(id);
    }

    @Override
    public void deleteAll() {
        e.deleteAll();
    }


    @Override
    public Etudiant editEtudiant(Long id, Etudiant etudiant) {
        if (e.findById(id).isPresent()) {
            Etudiant etudiant1 = e.findById(id).get();
            etudiant1.setNomEt(etudiant.getNomEt());
            etudiant1.setPrenomEt(etudiant.getPrenomEt());
            etudiant1.setCin(etudiant.getCin());
            etudiant1.setEmail(etudiant.getEmail());
            etudiant1.setEcole(etudiant.getEcole());
            etudiant1.setDateNaissance(etudiant.getDateNaissance());
            etudiant1.setMdp(etudiant.getMdp());
            return e.save(etudiant1);
        }
        return null;
    }


    @Override
    public List<Etudiant> findEtudiantsByEcole(String ecole) {
        return e.findEtudiantsByEcole(ecole);
    }

    @Override
    public List<Etudiant> findEtudiantByNomEtContaining(String s) {
        return e.findEtudiantByNomEtContaining(s);
    }

    @Override
    public Etudiant findEtudiantByEmail(String em) {
        return e.findEtudiantByEmail(em);
    }

    @Override
    public List<Etudiant> findEtudiantByDateNaissanceAfter(LocalDate date) {
        return e.findEtudiantByDateNaissanceAfter(date);
    }


    @Override
    public Etudiant findEtudiantByCin(Long cin) {
        return e.findEtudiantByCin(cin);
    }
    // ajouterEtudiantEtAffecterReservation
    @Override
    public Etudiant ajouterEtudiantEtAssignerReservation(String idreserv, Etudiant et) {
        //---------------------------------
        LocalDate dateDebutAU;
        LocalDate dateFinAU;
        int year = LocalDate.now().getYear() % 100;
        if (LocalDate.now().getMonthValue() <= 7) {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + (year - 1)), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + year), 6, 30);
        } else {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + year), 9, 15);
            dateFinAU = LocalDate.of(Integer.parseInt("20" + (year + 1)), 6, 30);
        }
        //---------------------------------;

        // Create a new Etudiant
        Etudiant etudiant = new Etudiant();
        // Set properties
        etudiant.setNomEt(et.getNomEt());
        etudiant.setPrenomEt(et.getPrenomEt());
        etudiant.setCin(et.getCin());
        etudiant.setEmail(et.getEmail());
        etudiant.setEcole(et.getEcole());
        etudiant.setDateNaissance(et.getDateNaissance());
        etudiant.setMdp(et.getMdp());

        // Find the reservation based on the provided id
        Reservation reserv = r.findByIdReservation(idreserv);


        // Create a new reserevation if not found
        if (reserv == null) {
            Reservation reserv1 = new Reservation();
            reserv1.setIdReservation(dateDebutAU.getYear() + "/" + dateFinAU.getYear() + "/" + idreserv);
            reserv1.setAnneeReservation(new Date());
            reserv1.setEstValide(true);
            // Associate the reservation with the student
            etudiant.getReservations().add(reserv1);

            // Associate the student with the reservation (many-to-many)
            reserv1.getEtudiants().add(etudiant);

            // Save the changes
            e.save(etudiant);
            r.save(reserv1);

        } else {
            // Associate the reservation with the student
            etudiant.getReservations().add(reserv);
// Associate the student with the reservation (many-to-many)
            reserv.getEtudiants().add(etudiant);
// Save the changes
            e.save(etudiant);
            r.save(reserv);

        }
        return etudiant;
    }
}

