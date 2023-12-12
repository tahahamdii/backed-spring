package com.example.foyerspring.Services;

import com.example.foyerspring.DAO.Entities.Foyer;
import com.example.foyerspring.DAO.Entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addUniversite(Universite u );

    List<Universite> addUniversites(List<Universite> foyers);
    Universite editUniversite(Long id, Universite u);
    List<Universite> findAll();
    Universite findById(long id);
    void deleteById(long id);
    void delete(Universite u);


    Universite getByNomUniverst(String nomUniversite);
    Universite getUniversiteByNomFoyer(String nomFoyer);
    List<Universite> getByAdresse(String adresse);

    Long getNombreTotalChambresByNomUniversite(String nomUniversite);

    List<Universite> getByNombreMinChambres(int nombreMinChambres) ;



    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite (long idUniversite) ;


}
