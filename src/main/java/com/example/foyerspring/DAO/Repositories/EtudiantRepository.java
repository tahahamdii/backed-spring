package com.example.foyerspring.DAO.Repositories;

import com.example.foyerspring.DAO.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    List<Etudiant> findEtudiantsByEcole(String ecole);
    Etudiant findEtudiantByCin(Long cin);
    List<Etudiant> findEtudiantByNomEtContaining(String s);
    Etudiant findEtudiantByEmail(String em);
    List<Etudiant> findEtudiantByDateNaissanceAfter(LocalDate date);

}
