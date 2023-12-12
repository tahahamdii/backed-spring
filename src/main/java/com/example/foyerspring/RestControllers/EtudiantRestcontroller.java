package com.example.foyerspring.RestControllers;

import com.example.foyerspring.DAO.Entities.Etudiant;
import com.example.foyerspring.Services.EtudiantService;
import com.example.foyerspring.Services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Etudiants")
public class EtudiantRestcontroller {

    @Autowired
    EtudiantService Service;

    @PostMapping("/addEtudiant")
    public Long addEtudiant(@RequestBody Etudiant e) {return Service.addEtudiant(e);}
    @PostMapping("/addEtudiants")
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> liste)
    {
        return Service.addAllEtudiant(liste);
    }

    @DeleteMapping("/deletebyid/{id}")
    void DeleteEtudiantByID(@PathVariable("id") Long id){
        Service.deleteById(id);
    }
    @DeleteMapping("/deleteall") String DeleteAll(){Service.deleteAll();return "all students are deleted";
    }
    @PutMapping("updateEtudiant/{id}")
    Etudiant updateEtudiant(@PathVariable("id") Long id ,@RequestBody Etudiant e){
        return  Service.editEtudiant(id,e);
    }


    @GetMapping("/getEtudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants(){
        List<Etudiant> liste=Service.getAllEtudiants();
        return new ResponseEntity<List<Etudiant>>(liste, HttpStatus.OK);
    }
    @GetMapping("/getby/{id}") Etudiant findById(@PathVariable("id") Long id){ return Service.findById(id);}

    @GetMapping("/getbyecole")
    public ResponseEntity<List<Etudiant>> findEtudiantsByEcole(@RequestParam("ecole") String ecole) {
        List<Etudiant> etudiants = Service.findEtudiantsByEcole(ecole);
        return ResponseEntity.ok(etudiants);
    }
    @GetMapping("/getbynomEtcontaining")
    public ResponseEntity<List<Etudiant>> findEtudiantByNomEtContaining(@RequestParam("nomEt") String s)
    {
        List<Etudiant> l=Service.findEtudiantByNomEtContaining(s);
        return ResponseEntity.ok(l);
    }
    @GetMapping("/getbyEmail")
    public ResponseEntity<Etudiant> findEtudiantByEmail(@RequestParam("email") String em)
    {
        Etudiant etudiant=Service.findEtudiantByEmail(em);
        return ResponseEntity.ok(etudiant);
    }
    @GetMapping("/getbydobafter")
    public ResponseEntity<List<Etudiant>> findEtudiantByDateNaissanceAfter(@RequestParam("dateNaissance") LocalDate date
    )
    {
        List<Etudiant> liste=Service.findEtudiantByDateNaissanceAfter(date);
        return ResponseEntity.ok(liste);
    }


    @GetMapping("/getbycin/{cin}")
    public ResponseEntity<Etudiant> findEtudiantBycin(@PathVariable("cin") Long cin){
        Etudiant l=Service.findEtudiantByCin(cin);
        return ResponseEntity.ok(l);}


    @PostMapping("/addetudiantwithreservation/{idreserv}")
    Etudiant  ajouterEtudiantEtAssignerReservation(@PathVariable("idreserv") String idreserv,@RequestBody Etudiant et){
        return Service.ajouterEtudiantEtAssignerReservation(idreserv,et );
    }












}
