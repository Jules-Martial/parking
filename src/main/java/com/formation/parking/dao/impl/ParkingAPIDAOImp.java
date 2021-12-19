package com.formation.parking.dao.impl;

import com.formation.parking.dao.ParkingAPIDAO;
import com.formation.parking.dao.entity.ResponseParkingAPIEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ParkingAPIDAOImp implements ParkingAPIDAO {
    private static final String URL_API_OPEN_DATA = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&facet=grp_nom&facet=grp_statut";
    /**
     * Appel API OPENDATA
     */
    @Autowired //Pour contacter l'API Open DATA
    private RestTemplate restTemplate;
    @Override
    public ResponseParkingAPIEntity getListeParkings() {

        return restTemplate.getForEntity(URL_API_OPEN_DATA, ResponseParkingAPIEntity.class).getBody();
    }
}
