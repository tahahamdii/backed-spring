package com.example.foyerspring.RestControllers;

import com.example.foyerspring.DAO.Entities.Reservation;
import com.example.foyerspring.Services.IReservationService;
import com.example.foyerspring.Services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationRestcontroller {
    @Autowired
    ReservationService Ser;
    @PostMapping("/addreservation")
    Reservation addReservation(@RequestBody Reservation r){
        return Ser.addReservation(r);
    }
    @GetMapping("/getallreservations")
    List<Reservation> findAll(){
        List<Reservation> l=Ser.getAllReservations();
        return l;
    }
    @GetMapping("getbyidreservation")
    Reservation getbyIdReservation(@RequestParam("idReservation") String id){
        return Ser.findByIdReservation(id);
    }


}
