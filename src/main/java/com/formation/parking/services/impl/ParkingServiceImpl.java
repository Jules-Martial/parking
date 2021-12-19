package com.formation.parking.services.impl;

import com.formation.parking.dao.ParkingAPIDAO;
import com.formation.parking.dao.entity.RecordEntity;
import com.formation.parking.dao.entity.ResponseParkingAPIEntity;
import com.formation.parking.models.Parking;
import com.formation.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    public ParkingAPIDAO parkingAPIDAO;

    @Override
    public List<Parking> getListeParkings() {
        ResponseParkingAPIEntity response = parkingAPIDAO.getListeParkings();

        return transformEntityToModel(response);
    }

    private List<Parking> transformEntityToModel(ResponseParkingAPIEntity response) {
        List<Parking> parkings = new ArrayList<>();
        for (RecordEntity record: response.getRecords()){
            Parking parking = new Parking();
            parking.setNom(record.getFields().getGrpNom());
            parking.setStatut(getLibelleStatut(record));
            parking.setNbPlacesDispo(record.getFields().getGrpDisponible());
            parking.setNbPlacesTotal(record.getFields().getGrpExploitation());
            parking.setHeureMaj(getHeureMaj(record));
            parkings.add(parking);
        }
        return parkings;
    }

    private String getHeureMaj(RecordEntity record) {
        OffsetDateTime dateMaj = OffsetDateTime.parse(record.getFields().getGrpHorodatage());
        OffsetDateTime dateMajWithOffSetPlus2 = dateMaj.withOffsetSameInstant(ZoneOffset.of("+02:00"));
        return dateMajWithOffSetPlus2.getHour()+ "h" + dateMajWithOffSetPlus2.getMinute();
    }

    private String getLibelleStatut(RecordEntity record) {
        switch (record.getFields().getGrpStatut()) {
            case "1": {
                return "FERME";
            }
            case "2": {
                return "ABONNES";
            }
            case "5": {
                return "OUVERT";
            }
        }
        return "Données non disponibles";
    }
}
