package com.example.foyerspring.DAO.Repositories;

import com.example.foyerspring.DAO.Entities.Bloc;
import com.example.foyerspring.DAO.Entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoyerRepository extends JpaRepository <Foyer,Long> {
    //1- Recherche d'un foyer d'un bloc spécifique
    List<Foyer> findByBloc(Bloc bloc);
    List<Foyer>findByNomFoyer(String nomFoyer);

    Foyer findFoyerByNomFoyer(String nomFoyer);

}
