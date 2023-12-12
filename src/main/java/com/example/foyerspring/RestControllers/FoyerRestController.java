package com.example.foyerspring.RestControllers;

import com.example.foyerspring.DAO.Entities.Bloc;
import com.example.foyerspring.DAO.Entities.Foyer;
import com.example.foyerspring.DAO.Entities.Universite;
import com.example.foyerspring.DAO.Repositories.BlocRepository;
import com.example.foyerspring.Services.IFoyerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Replace with the actual origin of your Angular app
@RequestMapping("/api/foyers")
public class FoyerRestController {
    IFoyerService iFoyerService;
    BlocRepository blocRepository;

    @GetMapping("findAllF")
    List<Foyer> findAll(){
        return iFoyerService.findAll();
    }

    @PostMapping("/addFoyer")
    Foyer addFoyer(@RequestBody Foyer f){
        return iFoyerService.addFoyer(f);
    }

    @PutMapping("UpdateFoyer")
    Foyer updateFoyer(@RequestBody Foyer f){
        return iFoyerService.editFoyer(f);
    }
    @PutMapping("/UpdateFoyer/{id}")
    Foyer updateFoyerId(@RequestBody Foyer f, @PathVariable long id) {
        f.setIdFoyer(id); // Set the ID from the path variable
        return iFoyerService.editFoyer(f);
    }
    @PutMapping("/UpdateFoyerWithAssociations/{id}/{idUniversite}/{idBloc}")
    Foyer updateFoyerWithAssociations(@RequestBody Foyer updatedFoyer, @PathVariable Long id,
                                      @PathVariable Long idUniversite, @PathVariable List<Long> idBloc) {
        return iFoyerService.updateFoyerWithAssociations(updatedFoyer, id, idUniversite, idBloc);
    }
    @DeleteMapping("DeleteFoyer")
    void DeleteFoyer(@RequestBody Foyer f){
        iFoyerService.delete(f);
    }

    @DeleteMapping("/DeleteFoyer/{id}")
    public void DeleteFoyerById(@PathVariable long id) {
        iFoyerService.deleteById(id);
    }
    @PostMapping("/ajouterFoyerEtAffecteUniversiteEtBloc/{idUniversite}/{idBloc}")
    public Foyer ajouterFoyerEtAffecteUniversiteAndBloc(@RequestBody Foyer foyer, @PathVariable long idUniversite, @PathVariable List<Long> idBloc) {
        return iFoyerService.addFoyerAndAssociateUniversiteAndBloc(foyer, idUniversite, idBloc);
    }

    @RequestMapping(value = "/DeleteFoyerAndDesaffecterUniversite/{id}", method = RequestMethod.DELETE)
    public void deleteFoyerAndDesaffecterUniversite(@PathVariable long id) {
        iFoyerService.deleteFoyerAndDesaffecterUniversite(id);
    }

}
