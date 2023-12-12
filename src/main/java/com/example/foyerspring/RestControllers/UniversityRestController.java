package com.example.foyerspring.RestControllers;

import com.example.foyerspring.DAO.Entities.Universite;
import com.example.foyerspring.Services.IUniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("universite")


public class UniversityRestController {
    @Autowired
    IUniversiteService iUniversiteService;

    @GetMapping("/findAll")
    List<Universite> findAll(){
        return  iUniversiteService.findAll();
    }

    @PostMapping("/add")
    Universite addUniversite(@RequestBody Universite u) {
        return iUniversiteService.addUniversite(u);
    }

    @PutMapping("update/{id}")
    Universite updateUniversite(@PathVariable("id") Long id, @RequestBody Universite u){
        return iUniversiteService.editUniversite(id, u);
    }

    @DeleteMapping("/delete/{id}")
    void deleteUniversite(@PathVariable("id") Long id){
        iUniversiteService.deleteById(id);
    }

    @GetMapping("findById/{id}")
    Universite findById(@PathVariable("id") Long id){
        return iUniversiteService.findById(id);
    }


    @GetMapping("/search/{nomUniversite}")
    Universite findByNom(@PathVariable("nomUniversite") String nomUniversite){
        return iUniversiteService.getByNomUniverst(nomUniversite);
    }
    @GetMapping("/byFoyer/{nomFoyer}")
    Universite  getUniversiteByNomFoyer(@PathVariable String nomFoyer) {
        return iUniversiteService.getUniversiteByNomFoyer(nomFoyer);

    }

    @GetMapping("/byAdresseUnivers/{adresse}")
    List<Universite> selectByAdresse(@PathVariable("adresse") String adresse){
        return  iUniversiteService.getByAdresse(adresse);
    }

    @GetMapping("/{nomUniversite}/statnombreTotalChambres")
    Long getNombreTotalChambres(@PathVariable String nomUniversite) {
        Long nombreTotalChambres = iUniversiteService.getNombreTotalChambresByNomUniversite(nomUniversite);
        return  nombreTotalChambres ;
    }

    @GetMapping("/nombreMinChambres/{nombreMinChambres}")
    List<Universite> findByNombreMinChambres(@PathVariable int nombreMinChambres) {
        List<Universite> universites = iUniversiteService.getByNombreMinChambres(nombreMinChambres);

        return universites ;
    }




    @PutMapping("/{idFoyer}/{nomUniversite}")
    Universite affecterFoyerAUniversite(@PathVariable("idFoyer") long idFoyer,
                                        @PathVariable("nomUniversite") String nomUniversite){
        return  iUniversiteService.affecterFoyerAUniversite(idFoyer,nomUniversite);
    }

    @PutMapping("/{idUniversite}")
    Universite desaffecterFoyerAUniversite(@PathVariable("idUniversite") long idUniversite){
        return  iUniversiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}

