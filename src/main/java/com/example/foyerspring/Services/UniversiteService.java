package com.example.foyerspring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.foyerspring.DAO.Entities.Foyer;
import com.example.foyerspring.DAO.Entities.Universite;
import com.example.foyerspring.DAO.Repositories.FoyerRepository;
import com.example.foyerspring.DAO.Repositories.UniversiteRepository;
import com.example.foyerspring.Services.FoyerService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UniversiteService implements IUniversiteService{
    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;


    @Override
    public Universite addUniversite(Universite u) {


        Foyer idFoyer = u.getFoyer();
        u.setFoyer(idFoyer);
        return universiteRepository.save(u);

    }

    @Override
    public List<Universite> addUniversites(List<Universite> universites) {
        return universiteRepository.saveAll(universites);
    }

    @Override
    public Universite editUniversite(Long id, Universite u) {
        if(universiteRepository.findById(id).isPresent()){
            Universite toUpdateUniversite = universiteRepository.findById(id).get();
            toUpdateUniversite.setNomUniversite(u.getNomUniversite());
            toUpdateUniversite.setAdresse(u.getAdresse());
            toUpdateUniversite.setFoyer(u.getFoyer());

            return universiteRepository.save(toUpdateUniversite);
        }
        return null;
    }

    @Override
    public List<Universite> findAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite findById(long id) {
        return universiteRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public void delete(Universite e) {
        universiteRepository.delete(e);
    }

    @Override
    public Universite getByNomUniverst(String nomUniversite) {
        return universiteRepository.findByNomUniversite(nomUniversite);
    }

    @Override
    public Universite getUniversiteByNomFoyer(String nomFoyer) {
        return universiteRepository.findUniversiteByFoyer_NomFoyer(nomFoyer);
    }

    @Override
    public List<Universite> getByAdresse(String adresse) {
        return universiteRepository.selectByAdresse(adresse);
    }

    @Override
    public Long getNombreTotalChambresByNomUniversite(String nomUniversite) {
        return universiteRepository.countChambresByNomUniversite(nomUniversite);
    }

    @Override
    public List<Universite> getByNombreMinChambres(int nombreMinChambres) {
        return universiteRepository.findByNombreMinChambres(nombreMinChambres);
    }


    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

        universite.setFoyer(foyer);
        universiteRepository.save(universite);

        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).get();
        universite.setFoyer(null);
        universiteRepository.save(universite);
        return universite;
    }
}
