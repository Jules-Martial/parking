package com.formation.parking.controllers;

import com.formation.parking.models.Parking;
import com.formation.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingController {
    @Autowired // Grâce à cette annotation on n'a pas besoin d'instantié la classe ParkingServiceImpl et les classes sont moins liées entre elles
    private ParkingService parkingService;

    //@CrossOrigin("http://localhost:4200")// Autorise les demandes venant de cet URL
    @CrossOrigin("http://localhost:4200")// Autorise les demandes venant de cet URL
    @RequestMapping(path = "/api/parkings",method = RequestMethod.GET)
    public List<Parking> getListeParking(){
        return parkingService.getListeParkings();
    }
}
