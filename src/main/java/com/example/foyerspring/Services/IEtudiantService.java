package com.example.foyerspring.Services;

import com.example.foyerspring.DAO.Entities.Etudiant;

import java.time.LocalDate;
import java.util.List;

public interface IEtudiantService {
    Long addEtudiant(Etudiant etudiant);

    List<Etudiant> addAllEtudiant(List<Etudiant> liste);

    List<Etudiant> getAllEtudiants();

    Etudiant findById(Long id);

    void deleteById(Long id);

    Etudiant editEtudiant(Long id, Etudiant etudiant);
    void deleteAll();
    List<Etudiant> findEtudiantsByEcole(String ecole);

    List<Etudiant>findEtudiantByNomEtContaining(String s);
    Etudiant findEtudiantByEmail(String em);
    List<Etudiant> findEtudiantByDateNaissanceAfter(LocalDate date);

    Etudiant findEtudiantByCin(Long cin);

    Etudiant ajouterEtudiantEtAssignerReservation(String idreserv,Etudiant et );



}
