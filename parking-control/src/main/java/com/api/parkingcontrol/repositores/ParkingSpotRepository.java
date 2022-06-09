package com.api.parkingcontrol.repositores;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    //boolean existsByLicensePLateCar(String licensePlateCar);
    // boolean existsByApartmentAndBlock( String apartment, String block);
    // boolean existsByParkingSpotNumber(String parkingSpotNumber);
    
}
